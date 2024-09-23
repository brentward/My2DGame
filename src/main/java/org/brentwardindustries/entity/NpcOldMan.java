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
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to find\nthe treasure?";
        dialogues[2] = "I used to be a great wizard but now...\nI too old for this shit!";
        dialogues[3] = "It is dangerous to go alone!";
    }

    public void setAction() {
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

    public void speak() {
        super.speak();
    }
}
