package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class KeyObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Key";

    public KeyObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = objectName;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nOpens a door.";
        price = 20;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door.";
        dialogues[1][0] = "You can't use a " + name + " here.";
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogState;
        boolean used = false;

        int objectIndex = getDetected(entity, gp.objects, DoorObject.objectName);
        if (objectIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.objects[gp.currentMap][objectIndex] = null;
            used = true;
        } else {
            startDialogue(this, 1);
        }
        return used;
    }
}
