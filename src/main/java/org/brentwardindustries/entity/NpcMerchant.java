package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.BootsObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.LanternObject;
import org.brentwardindustries.object.PickaxeObject;
import org.brentwardindustries.object.PotionBlueObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ReturnOrbObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.object.ShieldWoodObject;
import org.brentwardindustries.object.SwordBlueObject;
import org.brentwardindustries.object.SwordNormalObject;
import org.brentwardindustries.object.TentObject;

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
        dialogues[0][0] = "Ha ha, so you found me?\nI have some great crap.\nWant to trade?";
        dialogues[1][0] = "He He... thank you good sir.\nGood luck!";
        dialogues[2][0] =  "I'm sorry. You need more coins to buy that.\nThank you, small ya later!";
        dialogues[3][0] =  "You can't possibly carry any more items.";
        dialogues[4][0] =  "You cannot sell an equipped item!";
    }

    public void setItems() {
        inventory.add(new PotionRedObject(gp));
        inventory.add(new PotionBlueObject(gp));
        inventory.add(new ReturnOrbObject(gp));
        inventory.add(new TentObject(gp));
        inventory.add(new LanternObject(gp));
        inventory.add(new KeyObject(gp));
        inventory.add(new SwordNormalObject(gp));
        inventory.add(new AxeObject(gp));
        inventory.add(new PickaxeObject(gp));
        inventory.add(new ShieldWoodObject(gp));
        inventory.add(new SwordBlueObject(gp));
    }

    public void speak() {
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
