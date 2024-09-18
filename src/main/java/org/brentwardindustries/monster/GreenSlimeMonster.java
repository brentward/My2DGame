package org.brentwardindustries.monster;

import org.brentwardindustries.entity.Direction;
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
        speed = 1;
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
        int i = new Random().nextInt(100) + 1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 0) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 30;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if (i < 50) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new HeartObject(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new MagicCrystalObject(gp));
        }
    }
}
