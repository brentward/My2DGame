package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.DoorIronObject;
import org.brentwardindustries.tileinteractive.InteractiveMetalPlate;
import org.brentwardindustries.tileinteractive.InteractiveTile;

import java.util.ArrayList;

public class NpcBigRock extends Entity{
    public static final String npcName = "Big Rock";

    public NpcBigRock(GamePanel gp) {
        super(gp);

        name = npcName;
        direction = Direction.DOWN;
        speed = 4;

        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/npcs/bigrock", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "It's a giant rock, quite heavy.";
    }

    public void update() {}

    public void speak() {
        // Do character specific stuff
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
    }

    public void move(Direction direction) {
        this.direction = direction;

        checkCollision();

        if (!collisionOn) {
            switch (direction) {
                case UP -> worldY -= speed;
                case DOWN -> worldY += speed;
                case LEFT -> worldX -= speed;
                case RIGHT -> worldX += speed;
            }
        }

        detectPlate();
    }

    public void detectPlate() {
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        // Create plate list
        for (InteractiveTile interactiveTile : gp.interactiveTiles[gp.currentMap]) {
            if (interactiveTile != null && interactiveTile.name != null
                    && interactiveTile.name.equals(InteractiveMetalPlate.interactiveTileName)) {
                plateList.add(interactiveTile);
            }
        }
        // Create rock list
        for (Entity npc : gp.npcs[gp.currentMap]) {
            if (npc != null && npc.name.equals(NpcBigRock.npcName)) {
                rockList.add(npc);
            }
        }

        int count = 0;

        // Scan the plate list
        for (InteractiveTile plate : plateList) {
            int xDistance = Math.abs(worldX - plate.worldX);
            int yDistance = Math.abs(worldY - plate.worldY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 8) {
                if (linkedEntity == null) {
                    linkedEntity = plate;
                    gp.playSE(3);
                }
            } else {
                if (linkedEntity == plate) {
                    linkedEntity = null;
                }
            }
        }

        // Scan the rock list
        for (Entity rock : rockList) {
            // Count the rocks on plates
            if (rock.linkedEntity != null) {
                count++;
            }
        }

        // If all the rocks are on the plates, the iron door opens
        if (count == rockList.size()) {
            for (int i = 0; i < gp.objects[gp.currentMap].length; i++) {
                if (gp.objects[gp.currentMap][i] != null
                && gp.objects[gp.currentMap][i].name.equals(DoorIronObject.objectName)) {
                    gp.objects[gp.currentMap][i] = null;
                    gp.playSE(21);
                }
            }
        }
    }
}
