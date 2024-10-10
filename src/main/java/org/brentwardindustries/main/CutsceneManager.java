package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.PlayerDummy;
import org.brentwardindustries.monster.SkeletonLordMonster;
import org.brentwardindustries.object.BlueHeartObject;
import org.brentwardindustries.object.DoorIronObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2D;
    public int sceneNum;
    public int scenePhase;

    // Scene number
    public final int noScene = 0;
    public final int skeletonLordScene = 1;
    public final int endingScene = 2;

    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredits;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
        setEndCredits();
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        switch (sceneNum) {
            case skeletonLordScene -> playSkeletonLordScene();
            case endingScene -> playEndingScene();
        }
    }

    public void playSkeletonLordScene() {
        if (scenePhase == 0) {
            gp.bossBattleOn = true;
            for (int i = 0; i < gp.objects[gp.dungeonB2Map].length; i++) {
                if (gp.objects[gp.dungeonB2Map][i] == null) {
                    gp.objects[gp.dungeonB2Map][i] = new DoorIronObject(gp);
                    gp.objects[gp.dungeonB2Map][i].worldX = gp.tileSize * 25;
                    gp.objects[gp.dungeonB2Map][i].worldY = gp.tileSize * 28;
                    gp.objects[gp.dungeonB2Map][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            for (int i = 0; i < gp.npcs[gp.dungeonB2Map].length; i++) {
                if (gp.npcs[gp.dungeonB2Map][i] == null) {
                    gp.npcs[gp.dungeonB2Map][i] = new PlayerDummy(gp);
                    gp.npcs[gp.dungeonB2Map][i].worldX = gp.player.worldX;
                    gp.npcs[gp.dungeonB2Map][i].worldY = gp.player.worldY;
                    gp.npcs[gp.dungeonB2Map][i].direction = gp.player.direction;
                    break;
                }
            }


                    gp.player.drawing = false;
            scenePhase++;

        }
        if (scenePhase == 1) {
            gp.player.worldY -= 2;
            if (gp.player.worldY < gp.tileSize * 16) {
                scenePhase++;
            }
        }
        if (scenePhase == 2) {
            // Search for the boss
            for (Entity monster : gp.monsters[gp.dungeonB2Map]) {
                if (monster != null && monster.name.equals(SkeletonLordMonster.monsterName)) {
                    monster.sleep = false;
                    gp.ui.npc = monster;
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {
            // The boss speaks
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 4) {
            // Return camera to player

            // Search for the dummy
            for (int i = 0; i < gp.npcs[gp.dungeonB2Map].length; i++) {
                if (gp.npcs[gp.dungeonB2Map][i] != null
                        && gp.npcs[gp.dungeonB2Map][i].name.equals(PlayerDummy.npcName)) {
                    gp.player.worldX = gp.npcs[gp.dungeonB2Map][i].worldX;
                    gp.player.worldY = gp.npcs[gp.dungeonB2Map][i].worldY;
                    gp.npcs[gp.dungeonB2Map][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;
            sceneNum = noScene;
            scenePhase = 0;
            gp.gameState = gp.playState;
            gp.stopMusic();
            gp.playMusic(22);
        }
    }

    public void playEndingScene() {
        if (scenePhase == 0) {
//            gp.stopMusic();
            gp.ui.npc = new BlueHeartObject(gp);
            scenePhase++;
        }
        if (scenePhase == 1) {
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 2) {
            gp.stopMusic();
            gp.playSE(4);
            scenePhase++;
        }
        if (scenePhase == 3) {
            if (counterReached(300)) {
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            drawBlackBackground(alpha);
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 0f;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            drawBlackBackground(1f);
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            String text = """
                    After the fierce battle with the Skeleton Lord
                    The Boy made of Blue finally found the legendary Heart of Blue!
                    But this is not the end of his journey...
                    The Blue Boy's adventure has just begun.""";
            drawString(alpha, 38f, 200, text, 70);
            if (counterReached(600)) {
//                gp.playMusic(0);
                gp.playSE(21);
                alpha = 0f;
                scenePhase++;
            }

        }
        if (scenePhase == 6) {
            drawBlackBackground(1f);
            drawString(1f, 120f, gp.screenHeight / 2, "Blue Boy Adventure", 40);

            if (counterReached(480)) {
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if (scenePhase == 7) {
            drawBlackBackground(1f);
            y = gp.screenHeight / 2;
            drawString(alpha, 38f, y, endCredits, 40);
//            if (counterReached(480)) {
//                scenePhase++;
//            }
            alpha += 0.01f;
            if (alpha > 1f) {
                alpha = 0f;
                scenePhase++;
            }

        }
        if (scenePhase == 8) {
            drawBlackBackground(1f);

            int height = drawString(1f, 38f, y, endCredits, 40);
            if (y + height > gp.screenHeight / 2 + 40) {
                y--;
            } else {
                scenePhase++;
            }
        }
        if (scenePhase == 9) {
            drawBlackBackground(1f);

            drawString(1f, 38f, y, endCredits, 40);

            if (counterReached(180)) {
                scenePhase++;
            }

        }
        if (scenePhase == 10) {
            drawBlackBackground(1f);

            int height = drawString(1f, 38f, y, endCredits, 40);


            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            drawString(alpha, 38f, y + height + 80,
                    "(Press Enter to return to the title screen)", 40);
            if (gp.keyHandler.enterPressed) {
                gp.keyHandler.enterPressed = false;
                sceneNum = 0;
                scenePhase = 0;
                alpha = 0f;
                gp.stopMusic();
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }

        }
    }

    public boolean counterReached(int target) {
        boolean counterReached = false;
        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha) {
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public int drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        int height = 0;
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(fontSize));
        for (String line : text.split("\n")) {
            int x = gp.ui.getXForCenteredText(line);
            g2D.drawString(line, x, y);
            y += lineHeight;
            height += lineHeight;
        }
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        return height;
    }

    public void setEndCredits() {
        endCredits = """
                Program/Music/Art
                RyiSnow
                
                
                
                
                
                
                Implementation
                Brentward
                
                
                
                
                
                
                Testing
                Sam
                Brentward
                
                
                
                
                
                
                
                
                
                
                Thanks for playing!""";
    }
}
