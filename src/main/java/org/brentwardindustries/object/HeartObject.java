package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class HeartObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Heart";

    public HeartObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objectName;
        value = 2;
        int size = 32;
        image = setup("/objects/heart_full", size, size);
        image2 = setup("/objects/heart_half", size, size);
        image3 = setup("/objects/heart_blank", size, size);
        down1 = image;
        price = 6;
    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
        return true;
    }
}
