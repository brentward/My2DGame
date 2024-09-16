package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class SwordNormalObject extends Entity {
    public SwordNormalObject(GamePanel gp) {
        super(gp);

        name = Name.NORMAL_SWORD;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name.toString() + "]\nAn old sword.";
    }
}
