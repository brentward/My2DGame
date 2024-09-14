package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class ObjChest extends Entity {
    public ObjChest(GamePanel gp) {
        super(gp);

        name = Name.CHEST;
        down1 = setup("/objects/chest");
    }
}
