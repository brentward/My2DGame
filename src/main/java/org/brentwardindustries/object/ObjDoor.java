package org.brentwardindustries.object;

import org.brentwardindustries.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjDoor extends SuperObject {
    GamePanel gp;

    public ObjDoor(GamePanel gp) {
        this.gp = gp;

        name = Name.DOOR;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
