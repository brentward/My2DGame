package org.brentwardindustries.tileinteractive;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

public class InteractiveDryTree extends InteractiveTile{
    GamePanel gp;

    public InteractiveDryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon.type == typeAxe;
    }
    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() {
        return new InteractiveTrunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
    }
}
