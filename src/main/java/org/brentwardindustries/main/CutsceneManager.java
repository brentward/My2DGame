package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.PlayerDummy;
import org.brentwardindustries.monster.SkeletonLordMonster;
import org.brentwardindustries.object.DoorIronObject;

import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2D;
    public int sceneNum;
    public int scenePhase;

    // Scene number
    public final int noScene = 0;
    public final int skeletonLordScene = 1;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        switch (sceneNum) {
            case skeletonLordScene -> sceneSkeletonLord();
        }
    }

    public void sceneSkeletonLord() {
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
}
