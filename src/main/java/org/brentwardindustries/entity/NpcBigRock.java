package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;

import java.util.Random;

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
    }
}
