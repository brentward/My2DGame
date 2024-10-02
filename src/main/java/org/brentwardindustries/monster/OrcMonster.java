package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.util.Random;

public class OrcMonster extends Entity {
    GamePanel gp;
    public static final String objectName = "Orc";

    public OrcMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        name = objectName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        attackWindupDuration = 40;
        attackDuration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/monsters/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("/monsters/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monsters/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monsters/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monsters/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monsters/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monsters/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monsters/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monsters/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void setAction() {
        if (onPath) {
            // Check if stops chasing
            checkStopChasing(gp.player, 15, 100);

            // Search for path to the goal
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            // Check if it starts chasing
            checkStartChasing(gp.player, 5, 100);

            // Get a random direction if not on path
            getRandomDirection(120);
        }

        // Check if attacks
        if (!attacking) {
            checkAttack(30, gp.tileSize * 4, gp.tileSize);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {
        int die = new Random().nextInt(100) + 1;
        if (die < 50) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 50 && die < 75) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 75 && die < 100) {
            dropItem(new MagicCrystalObject(gp));
        }
    }
}
