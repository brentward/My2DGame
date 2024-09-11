package org.brentwardindustries.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {
    public ObjKey() {
        name = Name.KEY;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
