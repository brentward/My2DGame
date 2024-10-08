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
        boolean stillExists = true;

        if (gp.keyHandler.godModeOn) {
            if (collision) {
                boolean hasKey = false;
                for (int i = 0; i < gp.player.inventory.size(); i++) {
                    if (gp.player.inventory.get(i) != null && gp.player.inventory.get(i).name.equals(KeyObject.objectName)) {
                        if (gp.player.inventory.get(i).amount > 1) {
                            gp.player.inventory.get(i).amount--;
                        } else {
                            gp.player.inventory.remove(i);
                        }
                        hasKey = true;
                        break;
                    }
                }
                if (hasKey) {
                    gp.playSE(3);
                    stillExists = false;
                } else {
                    startDialogue(this, 0);
                }
            }
        } else {
            if (collision) {
                startDialogue(this, 0);
            }
        }

        return stillExists;
    }
}
