package org.brentwardindustries.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public GamePanel gp;

    // DEBUG
    public boolean showDebugText = false;
    public boolean showHitBox = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) { // TITLE STATE
            titleState(code);
        } else if (gp.gameState == gp.playState) { // PLAY STATE
            playState(code);
        } else if (gp.gameState == gp.pauseState) { // PAUSE STATE
            pauseState(code);
        } else if (gp.gameState == gp.dialogState) { // DIALOGUE STATE
            dialogueState(code);
        } else if (gp.gameState == gp.characterState) { //CHARACTER STATE
            characterState(code);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                // LOAD GAME
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.pauseMusic();
            gp.playSE(1);
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }

        if (code == KeyEvent.VK_R) {
            gp.tileManager.loadMap("/maps/worldV2.txt");
        }

        // SHOW HIT BOX
        if (code == KeyEvent.VK_F7) {
            showHitBox = !showHitBox;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
            gp.playSE(1);
            gp.resumeMusic();
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.slotRow--;
            if ( gp.ui.slotRow < 0) {
                gp.ui.slotRow = 3;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.slotCol--;
            if ( gp.ui.slotCol < 0) {
                gp.ui.slotCol = 4;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.slotRow++;
            if ( gp.ui.slotRow > 3) {
                gp.ui.slotRow = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_D) {
            gp.ui.slotCol++;
            if ( gp.ui.slotCol > 4) {
                gp.ui.slotCol = 0;
            }
            gp.playSE(9);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
