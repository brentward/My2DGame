package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class ObjKey extends Entity {
    public ObjKey(GamePanel gp) {
        super(gp);

        name = Name.KEY;
        down1 = setup("/objects/key");
    }
}
