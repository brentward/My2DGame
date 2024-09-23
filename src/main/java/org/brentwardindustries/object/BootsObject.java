package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class BootsObject extends Entity {
    public BootsObject(GamePanel gp) {
        super(gp);

        name = Name.BOOTS;
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        price = 25;
    }
}
