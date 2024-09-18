package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class MagicCrystalObject extends Entity {
    GamePanel gp;
    public MagicCrystalObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = Name.MAGIC_CRYSTAL;
        image = setup("/objects/magic_crystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/magic_crystal_blank", gp.tileSize, gp.tileSize);
    }
}
