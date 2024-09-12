package org.brentwardindustries.object;

import org.brentwardindustries.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {
    GamePanel gp;

    public ObjKey(GamePanel gp) {
        this.gp = gp;

        name = Name.KEY;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
