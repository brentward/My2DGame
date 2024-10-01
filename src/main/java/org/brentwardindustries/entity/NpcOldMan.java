package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;

import java.util.Random;

public class NpcOldMan extends Entity {
    public NpcOldMan(GamePanel gp) {
        super(gp);
        direction = Direction.DOWN;
        speed = 1;
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npcs/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npcs/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npcs/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npcs/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npcs/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npcs/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npcs/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npcs/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "Hello, lad.";
        dialogues[0][1] = "So you've come to this island to\nfind the treasure?";
        dialogues[0][2] = "I used to be a great wizard but now...\nI too old for this shit!";
        dialogues[0][3] = "It is dangerous to go alone!";
        dialogues[1][0] = "If you become tired, rest at the water.";
        dialogues[1][1] = "However, the monsters reappear if you rest.\nI don't know why but that's how it is.";
        dialogues[2][0] = "I wonder how to open that door...";
    }

    public void setAction() {
        if (onPath) {
//            int goalCol = 12;
//            int goalRow = 9;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/ gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = Direction.UP;
                }
                if (i > 25 && i <= 50) {
                    direction = Direction.DOWN;
                }
                if (i > 50 && i <= 75) {
                    direction = Direction.LEFT;
                }
                if (i > 75) {
                    direction = Direction.RIGHT;
                }
                actionLockCounter = 0;
            }
        }
    }

    public void speak() {
        // Do character specific stuff
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
    }
}
