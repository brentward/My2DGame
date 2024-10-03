package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class DoorIronObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Iron Door";

    public DoorIronObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeObstacle;
        name = objectName;
        image = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/blank", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "It won't budge";
    }

    public boolean interact() {
        if (collision) {
            startDialogue(this, 0);
        }
        return true;
    }
}
