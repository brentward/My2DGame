package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.CoinSilverObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.RockObject;
import org.brentwardindustries.object.ShieldWoodObject;

import java.util.Random;

public class RedSlimeMonster extends Entity {
    GamePanel gp;
    public static final String monsterName = "Red Slime";

    public RedSlimeMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        name = monsterName;
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 12;
        life = maxLife;
        attack = 7;
        defense = 4;
        exp = 5;
        projectile = new RockObject(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath) {
            // Check if stops chasing
            checkStopChasing(gp.player, 15, 100);

            // Search for path to the goal
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it shoots a projectile
            checkShootProjectile(120, 30);
        } else {
            // Check if it starts chasing
            checkStartChasing(gp.player, 5, 100);

            // Get a random direction if not on path
            getRandomDirection(80);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {
        int die = new Random().nextInt(100) + 1;
        if (die < 25) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 25 && die < 60) {
            dropItem(new CoinSilverObject(gp));
        }
        if (die >= 60 && die < 80) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 80 && die < 95) {
            dropItem(new PotionRedObject(gp));
        }
        if (die >= 95) {
            dropItem(new ShieldWoodObject(gp));
        }
    }
}
