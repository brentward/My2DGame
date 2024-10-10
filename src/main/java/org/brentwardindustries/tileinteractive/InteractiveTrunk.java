package org.brentwardindustries.tileinteractive;

import org.brentwardindustries.main.GamePanel;

public class InteractiveTrunk extends InteractiveTile{
    GamePanel gp;
    public static final String interactiveTileName = "Trunk";

    public InteractiveTrunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        name = interactiveTileName;
        down1 = setup("/tiles_interactive/trunk", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
