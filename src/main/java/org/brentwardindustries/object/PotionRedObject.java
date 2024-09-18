package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class PotionRedObject extends Entity {
    GamePanel gp;
    int value = 5;

    public PotionRedObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeConsumable;
        name = Name.RED_POTION;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nHeals your life by" + value + ".";
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogState;
        gp.ui.currentDialogue = "You dring the " + name.toString() + "!\n"
                + "Your life has recovered by " + value + "!";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
