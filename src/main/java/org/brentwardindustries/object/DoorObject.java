package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class DoorObject extends Entity {
    GamePanel gp;

    public DoorObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeObstacle;
        name = Name.DOOR;
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogState;
        gp.ui.currentDialogue = "You need a key to open this.";
    }
}
