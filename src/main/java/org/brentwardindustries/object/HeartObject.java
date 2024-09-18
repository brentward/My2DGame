package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class HeartObject extends Entity {
    GamePanel gp;

    public HeartObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = Name.HEART;
        value = 2;
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
        down1 = image;
    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
    }
}
