package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class PotionRedObject extends Entity {
    GamePanel gp;

    public PotionRedObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = Name.RED_POTION;
        value = 5;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nHeals your life by" + value + ".";
        price = 20;
    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        gp.gameState = gp.dialogState;
        gp.ui.currentDialogue = "You dring the " + name.toString() + "!\n"
                + "Your life has recovered by " + value + "!";
        entity.life += value;
        return true;
    }
}
