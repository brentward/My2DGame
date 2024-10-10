package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinGoldObject;
import org.brentwardindustries.object.CoinSilverObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.PotionBlueObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ReturnOrbObject;

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
        maxLife = 10;
        life = maxLife;
        attack = 9;
        defense = 3;
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
            getRandomDirection(15);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {
        int die = new Random().nextInt(100) + 1;
        if (die < 45) {
            dropItem(new CoinSilverObject(gp));
        }
        if (die >= 45 && die < 55) {
            dropItem(new CoinGoldObject(gp));
        }
        if (die >= 55 && die < 65) {
            dropItem(new ReturnOrbObject(gp));
        }
        if (die >= 65 && die < 75) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 75 && die < 85) {
            dropItem(new PotionRedObject(gp));
        }
        if (die >= 85) {
            dropItem(new PotionBlueObject(gp));
        }
    }
}
