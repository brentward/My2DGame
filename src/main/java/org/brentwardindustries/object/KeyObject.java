package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class KeyObject extends Entity {
    GamePanel gp;

    public KeyObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = Name.KEY;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nOpens a door.";
        price = 100;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogState;
        boolean used = false;

        int objectIndex = getDetected(entity, gp.objects, Name.DOOR);
        if (objectIndex != 999) {
            gp.ui.currentDialogue = "You use the " + name.toString() + " and open the door.";
            gp.playSE(3);
            gp.objects[gp.currentMap][objectIndex] = null;
            used = true;
        } else {
            gp.ui.currentDialogue = "You can't use a " + name.toString() + " here.";
        }
        return used;
    }
}
