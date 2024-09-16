package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class ShieldWoodObject extends Entity {
    public ShieldWoodObject(GamePanel gp) {
        super(gp);

        name = Name.WOOD_SHIELD;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
