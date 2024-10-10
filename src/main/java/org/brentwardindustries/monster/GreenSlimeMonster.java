package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.util.Random;

public class GreenSlimeMonster extends Entity {
    GamePanel gp;
    public static final String monsterName = "Green Slime";

    public GreenSlimeMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        name = monsterName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 2;

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
        if (!onPath) {
            getRandomDirection(100);
        }
    }

    public void damageReaction() {
        getRandomDirection(0);
        actionLockCounter = 0;
    }

    public void checkDrop() {
        int die = new Random().nextInt(100) + 1;
        if (die < 60) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 60 && die < 70) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 70 && die < 90) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 90 && die < 100) {
            dropItem(new MagicCrystalObject(gp));
        }
    }
}
