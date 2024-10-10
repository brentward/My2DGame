package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class CoinGoldObject extends Entity {
    GamePanel gp;
    public static final String objectName = "Gold Coin";

    public CoinGoldObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objectName;
        value = 20;
        down1 = setup("/objects/coin_gold", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
        return true;
    }
}
