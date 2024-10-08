package org.brentwardindustries.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed,
            guardPressed;

    // DEBUG
    public boolean showDebugText = false;
    public boolean showHitBox = false;
    public boolean showPaths = false;
    public boolean godModeOn = false;
    public boolean levelUpPressed = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        inputPressed(code);
    }

    public void inputPressed(int code) {
        if (gp.gameState == gp.titleState) { // TITLE STATE
            titleState(code);
        } else if (gp.gameState == gp.playState) { // PLAY STATE
            playState(code);
        } else if (gp.gameState == gp.pauseState) { // PAUSE STATE
            pauseState(code);
        } else if (gp.gameState == gp.dialogState // DIALOGUE STATE
                || gp.gameState == gp.cutsceneState) { // CUTSCENE STATE
            dialogueState(code);
        } else if (gp.gameState == gp.characterState) { //CHARACTER STATE
            characterState(code);
        } else if (gp.gameState == gp.optionsState) { // OPTIONS STATE
            optionsState(code);
        } else if (gp.gameState == gp.gameOverState) { // GAME OVER STATE
            gameOverState(code);
        } else if (gp.gameState == gp.tradeState) { // TRADE STATE
            tradeState(code);
        } else if (gp.gameState == gp.mapState) { // MAP STATE
            mapState(code);
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
//                gp.resetGame(true);
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                // LOAD GAME
                if (gp.saveLoad.load()) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
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
            gp.stopMusic();
            gp.playSE(1);
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X) {
            gp.map.miniMapOn = !gp.map.miniMapOn;
        }
        if (code == KeyEvent.VK_SPACE) {
            guardPressed = true;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }
        if (code == KeyEvent.VK_G) {
            godModeOn = !godModeOn;
        }

        if (code == KeyEvent.VK_L) {
            levelUpPressed = true;
        }

        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0 -> gp.tileManager.loadMap("/maps/worldmap.txt", 0);
                case 1 -> gp.tileManager.loadMap("/maps/indoor01.txt", 1);
            }
        }

        // SHOW HIT BOX
        if (code == KeyEvent.VK_F7) {
            showHitBox = !showHitBox;
        }

        // SHOW PATHS
        if (code == KeyEvent.VK_F8) {
            showPaths = !showPaths;
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
            enterPressed = true;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            gp.ui.commandNum = 0;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0 -> maxCommandNum = 5;
            case 3 -> maxCommandNum = 1;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolue();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolue();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.resetGame(false);
                gp.playMusic(0);
                gp.gameState = gp.playState;
            } else if (gp.ui.commandNum == 1) {
                gp.ui.commandNum = 2;
                gp.resetGame(true);
                gp.gameState = gp.titleState;
            }
        }
    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                gp.playSE(9);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                gp.playSE(9);
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
    }

    public void mapState(int code) {
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.playerSlotRow--;
            if ( gp.ui.playerSlotRow < 0) {
                gp.ui.playerSlotRow = 3;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.playerSlotCol--;
            if ( gp.ui.playerSlotCol < 0) {
                gp.ui.playerSlotCol = 4;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.playerSlotRow++;
            if ( gp.ui.playerSlotRow > 3) {
                gp.ui.playerSlotRow = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_D) {
            gp.ui.playerSlotCol++;
            if ( gp.ui.playerSlotCol > 4) {
                gp.ui.playerSlotCol = 0;
            }
            gp.playSE(9);
        }
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.npcSlotRow--;
            if ( gp.ui.npcSlotRow < 0) {
                gp.ui.npcSlotRow = 3;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.npcSlotCol--;
            if ( gp.ui.npcSlotCol < 0) {
                gp.ui.npcSlotCol = 4;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.npcSlotRow++;
            if ( gp.ui.npcSlotRow > 3) {
                gp.ui.npcSlotRow = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_D) {
            gp.ui.npcSlotCol++;
            if ( gp.ui.npcSlotCol > 4) {
                gp.ui.npcSlotCol = 0;
            }
            gp.playSE(9);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        inputReleased(code);
    }

    public void inputReleased(int code) {
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
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            guardPressed = false;
        }
    }
}
