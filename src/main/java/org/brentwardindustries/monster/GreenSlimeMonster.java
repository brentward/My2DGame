package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;
import org.brentwardindustries.object.RockObject;

import java.util.Random;

public class GreenSlimeMonster extends Entity {
    GamePanel gp;
    public GreenSlimeMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        name = Name.GREEN_SLIME;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
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
        up1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath) {
            // Check if stops chasing
            checkStopChasing(gp.player, 15, 100);

            // Search for path to the goal
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it shoots a projectile
            checkShootProjectile(200, 30);
        } else {
            // Check if it starts chasing
            checkStartChasing(gp.player, 5, 100);

            // Get a random direction if not on path
            getRandomDirection(120);
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
