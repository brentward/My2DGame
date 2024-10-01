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
        image = setup("/objects/door", gp.tileSize, gp.tileSize);
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
        dialogues[0][0] = "You need a key to open this.";
    }

    public boolean interact() {
        boolean stillExists = true;
        if (collision) {
            boolean hasKey = false;
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (gp.player.inventory.get(i) != null && gp.player.inventory.get(i).name == Name.KEY) {
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
        return stillExists;
    }
}
