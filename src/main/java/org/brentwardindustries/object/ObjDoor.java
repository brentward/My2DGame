package org.brentwardindustries.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjDoor extends SuperObject {
    public ObjDoor() {
        name = Name.DOOR;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
