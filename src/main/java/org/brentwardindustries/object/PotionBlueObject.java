package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class PotionBlueObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Blue Potion";

    public PotionBlueObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = objectName;
        value = 5;
        down1 = setup("/objects/potion_blue", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRefills your magic by " + value + ".";
        price = 20;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your magic has recovered by " + value + "!";
    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        startDialogue(this, 0);
        entity.magic += value;
        gp.playSE(2);
        return true;
    }
}
