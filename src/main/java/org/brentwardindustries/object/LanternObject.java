package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class LanternObject extends Entity {
    public LanternObject(GamePanel gp) {
        super(gp);

        type = typeLight;
        name = Name.LANTERN;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[" + name.toString() + "]\nIlluminates your\nsurroundings.";
        price = 200;
        lightRadius = 250;
    }
}
