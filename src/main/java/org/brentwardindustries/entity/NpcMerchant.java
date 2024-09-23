package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.*;

public class NpcMerchant extends Entity{
    public NpcMerchant(GamePanel gp) {
        super(gp);
        direction = Direction.DOWN;
        speed = 1;
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/npcs/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npcs/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npcs/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npcs/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npcs/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npcs/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npcs/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npcs/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Ha ha, so you found me?\nI have some great crap.\nWant to trade?";
    }

    public void setItems() {
        inventory.add(new PotionRedObject(gp));
        inventory.add(new KeyObject(gp));
        inventory.add(new SwordNormalObject(gp));
        inventory.add(new AxeObject(gp));
        inventory.add(new ShieldWoodObject(gp));
        inventory.add(new ShieldBlueObject(gp));
    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
