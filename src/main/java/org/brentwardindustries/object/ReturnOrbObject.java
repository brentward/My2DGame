package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Player;
import org.brentwardindustries.main.GamePanel;

public class ReturnOrbObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Return Orb";

    public ReturnOrbObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = objectName;
        this.down1 = setup("/objects/returnorb", gp.tileSize, gp.tileSize);
        this.description = "[" + name + "]\nTeleport to the\nsave point.";
        this.price = 5;
        this.stackable = true;
        this.setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + "!";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        gp.playSE(2);
        gp.nextArea = gp.outsideArea;
        gp.currentMap = gp.worldMap;
        gp.player.worldX = gp.tileSize * 23;
        gp.player.worldY = gp.tileSize * 13;
        gp.changeArea();
        return true;
    }
}
