package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class SwordMagicObject extends Entity {
    public static final String objectName = "Magic Sword";

    public SwordMagicObject(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = objectName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 3;
        attackArea.width = 42;
        attackArea.height = 42;
        description = "[" + name + "]\nA sword made of blue.";
        price = 400;
        knockBackPower = 1;
        attackWindupDuration = 1;
        attackDuration = 4;
    }
}
