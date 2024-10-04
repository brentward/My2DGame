package org.brentwardindustries.main;

import org.brentwardindustries.entity.Direction;
import org.brentwardindustries.entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    int tempMap, tempCol, tempRow;
    Entity voiceOfGod;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        voiceOfGod = new Entity(gp);
        setDialogue();

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void setDialogue() {
        voiceOfGod.dialogues[0][0] = "You fell into a pit!";
        voiceOfGod.dialogues[1][0] = "You drink the water...\nYour life and magic have been restored.";
        voiceOfGod.dialogues[1][1] = "Damn, this is good water.";
        voiceOfGod.dialogues[1][2] = "The monsters have returned!";
        voiceOfGod.dialogues[1][3] = "(Progress has been saved)";
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(gp.worldMap, 27, 16, Direction.RIGHT)) {
                damagePit(gp.dialogState);
            }
            if (hit(gp.worldMap, 23, 12, Direction.UP)) {
                healingPool(gp.dialogState);
            } else if (hit(gp.worldMap, 10, 39, Direction.UP)) { // from Merchant's house world map
                teleport(gp.indoorMap, 12, 13, gp.indoorArea); // to Merchant's house indoors
            } else if (hit(gp.indoorMap, 12, 13, Direction.DOWN )) { // from Merchant's house indoors
                teleport(gp.worldMap, 10, 39, gp.outsideArea); // to Merchant's house map
            } else if (hit(gp.indoorMap, 12, 9, Direction.UP)) {
                speak(gp.npcs[1][0]);
            } else if (hit(gp.worldMap, 12, 9, Direction.ANY)) { // from world map stairs
                teleport(gp.dungeonB1Map, 9, 41, gp.dungeonArea); // to the dungeon
            } else if (hit(gp.dungeonB1Map, 9, 41, Direction.ANY)) { // from the dungeon
                teleport(gp.worldMap, 12, 9, gp.outsideArea); // to world map stairs
            } else if (hit(gp.dungeonB1Map, 8, 7, Direction.ANY)) { // from B1
                teleport(gp.dungeonB2Map, 26, 41, gp.dungeonArea); // to B2
            } else if (hit(gp.dungeonB2Map, 26, 41, Direction.ANY)) { // from B2
                teleport(gp.dungeonB1Map, 8, 7, gp.dungeonArea); // to B1
            }
        }
    }

    public boolean hit(int map, int col, int row, Direction reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction == reqDirection || reqDirection == Direction.ANY) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;

    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.playSE(6);
        voiceOfGod.startDialogue(voiceOfGod, 0);
        gp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyHandler.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            voiceOfGod.startDialogue(voiceOfGod, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.magic = gp.player.maxMagic;
            gp.assetSetter.setMonsters();
            gp.saveLoad.save();
        }
    }

    public void teleport(int map, int col, int row, int area) {
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }

    public void speak(Entity entity) {
        if (gp.keyHandler.enterPressed) {
            gp.gameState = gp.dialogState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
}
