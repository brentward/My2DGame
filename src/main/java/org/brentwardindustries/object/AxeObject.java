package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class AxeObject extends Entity {
    public static final String objectName = "Woodcutter's Axe";

    public AxeObject(GamePanel gp) {
        super(gp);

        type = typeAxe;
        name = objectName;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 4;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nA bit rusty but can still\nfell foul beasts.";
        price = 75;
        knockBackPower = 10;
        attackWindupDuration = 20;
        attackDuration = 40;
    }
}
