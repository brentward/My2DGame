package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class PotionRedObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Red Potion";

    public PotionRedObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = objectName;
        value = 5;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
        price = 20;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your life has recovered by " + value + "!";
    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        startDialogue(this, 0);
        entity.life += value;
        return true;
    }
}
