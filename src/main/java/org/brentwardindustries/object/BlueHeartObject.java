package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class BlueHeartObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Heart of Blue";

    public BlueHeartObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objectName;
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You pick up a beautiful gem made of Blue!";
        dialogues[0][1] = "You found the " + this.name + ", the legendary treasure! ";
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState;
        gp.cutsceneManager.sceneNum = gp.cutsceneManager.endingScene;
        return true;
    }
}
