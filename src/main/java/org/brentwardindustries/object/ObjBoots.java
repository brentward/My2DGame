package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class ObjBoots  extends Entity {
    public ObjBoots(GamePanel gp) {
        super(gp);

        name = Name.BOOTS;
        down1 = setup("/objects/boots");
    }
}
