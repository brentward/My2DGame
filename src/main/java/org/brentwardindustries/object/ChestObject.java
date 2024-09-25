package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class ChestObject extends Entity {
    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public ChestObject(GamePanel gp, Entity loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = typeObstacle;
        name = Name.CHEST;

        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogState;
        if (!opened) {
            gp.playSE(3);
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and found a ").append(loot.name).append("!");
            if (!gp.player.canObtainItem(loot)) {
                sb.append("\n... But you cannot carry more items!");
            } else {
                sb.append("\nYou obtained a ").append(loot.name);
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        } else {
            gp.ui.currentDialogue = "It is empty.";
        }
    }
}
