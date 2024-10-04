package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class PickaxeObject extends Entity {
    public static final String objectName = "Pickaxe";

    public PickaxeObject(GamePanel gp) {
        super(gp);

        type =typePickaxe;
        name = objectName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nOften used in mining\n... and crafting?";
        price = 75;
        knockBackPower = 10;
        attackWindupDuration = 10;
        attackDuration = 20;
    }
}
