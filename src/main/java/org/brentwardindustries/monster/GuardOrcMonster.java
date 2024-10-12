package org.brentwardindustries.monster;

import org.brentwardindustries.data.Progress;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.KeyObject;

public class GuardOrcMonster extends OrcMonster {
    public GuardOrcMonster(GamePanel gp) {
        super(gp);
    }

    public void checkDrop() {
        if (!Progress.guardOrcDefeated) {
            dropItem(new KeyObject(gp));
            Progress.guardOrcDefeated = true;
        } else {
            super.checkDrop();
        }
    }
}
