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

        int objectIndex = getDetected(entity, gp.objects, Name.DOOR);
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
