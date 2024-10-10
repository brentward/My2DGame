package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class SwordBlueObject extends Entity {
    public static final String objectName = "Sword of Blue";

    public SwordBlueObject(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = objectName;
        down1 = setup("/objects/sword_blue", gp.tileSize, gp.tileSize);
        attackValue = 3;
        attackArea.width = 42;
        attackArea.height = 42;
        description = "[" + name + "]\nMade of Blue.";
        price = 200;
        knockBackPower = 4;
        attackWindupDuration = 3;
        attackDuration = 10;
    }
}
