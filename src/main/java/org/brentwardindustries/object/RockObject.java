package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.entity.Projectile;
import org.brentwardindustries.main.GamePanel;

public class RockObject extends Projectile {
    GamePanel gp;

    public RockObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = Name.ROCK;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    public void getImage() {
        up1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }
}