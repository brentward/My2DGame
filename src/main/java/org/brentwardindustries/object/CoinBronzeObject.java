package org.brentwardindustries.object;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Name;
import org.brentwardindustries.main.GamePanel;

public class CoinBronzeObject extends Entity {
    GamePanel gp;

    public CoinBronzeObject(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = Name.BRONZE_COIN;
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
