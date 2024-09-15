package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class ChestObject extends Entity {
    public ChestObject(GamePanel gp) {
        super(gp);

        name = Name.CHEST;
        down1 = setup("/objects/chest");
    }
}
