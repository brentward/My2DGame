package org.brentwardindustries.object;

import org.brentwardindustries.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChest extends SuperObject {
    GamePanel gp;

    public ObjChest(GamePanel gp) {
        this.gp = gp;

        name = Name.CHEST;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
