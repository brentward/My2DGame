package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class AxeObject extends Entity {
    public AxeObject(GamePanel gp) {
        super(gp);

        type = typeAxe;
        name = Name.WOODCUTTERS_AXE;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name.toString() + "]\nA bit rusty but can still\nfell foul beasts.";
        price = 75;
    }
}
