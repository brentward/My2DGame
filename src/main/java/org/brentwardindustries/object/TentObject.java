package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class TentObject extends Entity {
    GamePanel gp;

    public TentObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = Name.TENT;
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nYou can sleep until\nnext morning.";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.magic = gp.player.maxMagic;
        gp.player.getPlayerSleepingImage(down1);
        return true;
    }
}
