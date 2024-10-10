package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class LanternObject extends Entity {
    public static final String objectName = "Lantern";

    public LanternObject(GamePanel gp) {
        super(gp);

        type = typeLight;
        name = objectName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIlluminates your\nsurroundings.";
        price = 45;
        lightRadius = 350;
    }
}
