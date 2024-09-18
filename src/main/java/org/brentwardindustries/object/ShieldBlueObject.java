package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class ShieldBlueObject extends Entity {
    public ShieldBlueObject(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = Name.BLUE_SHIELD;
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 4;
        description = "[" + name.toString() + "]\nMade of blue.";
    }
}
