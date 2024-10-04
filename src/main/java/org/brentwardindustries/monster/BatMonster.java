package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.util.Random;

public class BatMonster extends Entity {
    GamePanel gp;
    public static final String monsterName = "Bat";

    public BatMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        name = monsterName;
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monsters/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/bat_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (!onPath) {
            getRandomDirection(10);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
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
