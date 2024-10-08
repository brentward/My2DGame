package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class TentObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Tent";

    public TentObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = objectName;
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nSleep until morning.\nRecover life and magic.";
        price = 30;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.magic = gp.player.maxMagic;
        gp.player.setSleepImage(down1);
        return true;
    }
}
