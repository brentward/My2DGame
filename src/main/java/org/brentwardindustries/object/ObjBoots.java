package org.brentwardindustries.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjBoots  extends SuperObject {
    public ObjBoots() {
        name = Name.BOOTS;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
