package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class ShieldWoodObject extends Entity {
    public static final String objectName = "Wood Shield";

    public ShieldWoodObject(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = objectName;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        knockBackPower = 2;
        description = "[" + name + "]\nMade of wood.";
        price = 35;
    }
}
