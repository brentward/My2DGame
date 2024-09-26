package org.brentwardindustries.environment;

import org.brentwardindustries.main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp, int circleSize) {
        this.gp = gp;

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D g2D = (Graphics2D) darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        int centerX = gp.player.screenX + gp.halfTileSize;
        int centerY = gp.player.screenY + gp.halfTileSize;

        double x = (double) centerX - ((double) circleSize / 2d);
        double y = (double) centerY - ((double) circleSize / 2d);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);

        screenArea.subtract(lightArea);

        // CREATE GRADATION EFFECT
        Color[] colors = new Color[12];
        float[] fractions =  new float[12];

        colors[0] = new Color(0, 0, 0, 0.1f);
        colors[1] = new Color(0, 0, 0, 0.42f);
        colors[2] = new Color(0, 0, 0, 0.52f);
        colors[3] = new Color(0, 0, 0, 0.61f);
        colors[4] = new Color(0, 0, 0, 0.69f);
        colors[5] = new Color(0, 0, 0, 0.76f);
        colors[6] = new Color(0, 0, 0, 0.82f);
        colors[7] = new Color(0, 0, 0, 0.87f);
        colors[8] = new Color(0, 0, 0, 0.91f);
        colors[9] = new Color(0, 0, 0, 0.94f);
        colors[10] = new Color(0, 0, 0, 0.96f);
        colors[11] = new Color(0, 0, 0, 0.98f);

        fractions[0] = 0.0f;
        fractions[1] = 0.4f;
        fractions[2] = 0.5f;
        fractions[3] = 0.6f;
        fractions[4] = 0.65f;
        fractions[5] = 0.7f;
        fractions[6] = 0.75f;
        fractions[7] = 0.8f;
        fractions[8] = 0.85f;
        fractions[9] = 0.9f;
        fractions[10] = 0.95f;
        fractions[11] = 1.0f;

        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, (float) circleSize / 2f, fractions, colors);

        g2D.setPaint(gradientPaint);

        g2D.fill(lightArea);

        g2D.fill(screenArea);
        g2D.dispose();
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(darknessFilter, 0, 0, null);
    }
}
