package org.brentwardindustries.object;

import org.brentwardindustries.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjBoots  extends SuperObject {
    GamePanel gp;

    public ObjBoots(GamePanel gp) {
        this.gp = gp;

        name = Name.BOOTS;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
