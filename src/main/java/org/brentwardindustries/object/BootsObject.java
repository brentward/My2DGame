package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class BootsObject extends Entity {
    public static final String objectName = "Boots";
    public BootsObject(GamePanel gp) {
        super(gp);

        name = objectName;
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        price = 25;
    }
}
