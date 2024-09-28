package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class SwordNormalObject extends Entity {
    public SwordNormalObject(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = Name.NORMAL_SWORD;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name.toString() + "]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        attackWindupDuration = 5;
        attackDuration = 25;
    }
}
