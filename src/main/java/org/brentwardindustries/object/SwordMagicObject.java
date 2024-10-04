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
        attackValue = 4;
        attackArea.width = 48;
        attackArea.height = 48;
        description = "[" + name + "]\nA magical sword.";
        price = 200;
        knockBackPower = 2;
        attackWindupDuration = 1;
        attackDuration = 5;
    }
}
