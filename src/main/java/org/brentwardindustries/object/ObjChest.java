package org.brentwardindustries.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChest extends SuperObject {
    public boolean opened = false;
    public ObjChest() {
        name = Name.CHEST;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
