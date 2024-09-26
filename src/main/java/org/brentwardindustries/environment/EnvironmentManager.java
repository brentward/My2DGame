package org.brentwardindustries.environment;

import org.brentwardindustries.main.GamePanel;

import java.awt.Graphics2D;

public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setup(){
        lighting = new Lighting(gp, 350);
    }

    public void draw(Graphics2D g2D) {
        lighting.draw(g2D);
    }
}
