package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class MagicCrystalObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Magic Crystal";

    public MagicCrystalObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objectName;
        value = 1;
        image = setup("/objects/magic_crystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/magic_crystal_blank", gp.tileSize, gp.tileSize);
        down1 = image;
        price = 6;
    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Magic +" + value);
        entity.magic += value;
        return  true;
    }
}
