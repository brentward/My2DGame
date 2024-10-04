package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class ShieldBlueObject extends Entity {
    public static final String objectName = "Shield of Blue";

    public ShieldBlueObject(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = objectName;
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 4;
        knockBackPower = 3;
        description = "[" + name + "]\nMade of blue.";
        price = 250;
    }
}
