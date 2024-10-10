package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Projectile;
import org.brentwardindustries.main.GamePanel;

import java.awt.Color;

public class FireballObject extends Projectile {
    GamePanel gp;
    public static final String objectName = "Fireball";

    public FireballObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objectName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 5;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/fireball_right_2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        return user.magic >= useCost;
    }

    public void subtractResource(Entity user) {
        user.magic -= useCost;
    }

    public Color getParticleColor() {
        return new Color(240, 50, 0);
    }

    public int getParticleSize() {
        return 10;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
