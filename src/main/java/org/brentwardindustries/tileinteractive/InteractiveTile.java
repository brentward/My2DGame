package org.brentwardindustries.tileinteractive;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;
    public static final String interactiveTileName = "Interactive Tile";


    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        name = interactiveTileName;
    }

    public boolean isCorrectItem(Entity entity) {
        return  false;
    }

    public void playSE() {}

    public InteractiveTile getDestroyedForm() {
        return null;
    }

    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
