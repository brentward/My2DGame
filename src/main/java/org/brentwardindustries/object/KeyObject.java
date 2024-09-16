package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class KeyObject extends Entity {
    public KeyObject(GamePanel gp) {
        super(gp);

        name = Name.KEY;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nOpens a door.";
    }
}
