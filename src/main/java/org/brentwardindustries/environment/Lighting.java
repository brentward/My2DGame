package org.brentwardindustries.environment;

import org.brentwardindustries.main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    int dayCounter;
    public float filterAlpha = 0f;
    final float nightAlpha = 0.98f;

    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    int dayState = day;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D g2D = (Graphics2D) darknessFilter.getGraphics();

        if (gp.player.currentLight == null) {
            g2D.setColor(new Color(0, 0, 0, nightAlpha));
        } else {

            int centerX = gp.player.screenX + gp.halfTileSize;
            int centerY = gp.player.screenY + gp.halfTileSize;

            // CREATE GRADATION EFFECT
            Color[] colors = new Color[12];
            float[] fractions = new float[12];

            colors[0] = new Color(0, 0, 0, 0.1f * nightAlpha);
            colors[1] = new Color(0, 0, 0, 0.42f * nightAlpha);
            colors[2] = new Color(0, 0, 0, 0.52f * nightAlpha);
            colors[3] = new Color(0, 0, 0, 0.61f * nightAlpha);
            colors[4] = new Color(0, 0, 0, 0.69f * nightAlpha);
            colors[5] = new Color(0, 0, 0, 0.76f * nightAlpha);
            colors[6] = new Color(0, 0, 0, 0.82f * nightAlpha);
            colors[7] = new Color(0, 0, 0, 0.87f * nightAlpha);
            colors[8] = new Color(0, 0, 0, 0.91f * nightAlpha);
            colors[9] = new Color(0, 0, 0, 0.94f * nightAlpha);
            colors[10] = new Color(0, 0, 0, 0.96f * nightAlpha);
            colors[11] = new Color(0, 0, 0, 0.98f * nightAlpha);

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

            RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, (float) gp.player.currentLight.lightRadius, fractions, colors);

            g2D.setPaint(gradientPaint);
        }

        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2D.dispose();
    }

    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
        dayCounter = 0;
    }

    public void update() {
        if (gp.player.lightUpdated) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        // Check state of the day
        if (dayState == day) {
            dayCounter++;

            if (dayCounter > 600) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;
            if (filterAlpha > nightAlpha) {
                filterAlpha = nightAlpha;
                dayState = night;
            }
        }
        if (dayState == night) {
            dayCounter++;

            if (dayCounter > 600) {
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if (dayState == dawn) {
            filterAlpha -= 0.001f;
            if (filterAlpha < 0.0f) {
                filterAlpha = 0;
                dayState = day;
            }
        }

    }

    public void draw(Graphics2D g2D) {
        if (gp.currentArea == gp.outsideArea) {
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }
        if (gp.currentArea == gp.outsideArea || gp.currentArea == gp.dungeonArea) {
            g2D.drawImage(darknessFilter, 0, 0, null);
        }
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        if (gp.keyHandler.showDebugText) {
            // DEBUG
            String situation = "";
            switch (dayState) {
                case day -> situation = "Day";
                case dusk -> situation = "Dusk";
                case night -> situation = "Night";
                case dawn -> situation = "Dawn";
            }
            g2D.setColor(Color.WHITE);
            g2D.setFont(g2D.getFont().deriveFont(50f));
            g2D.drawString(situation, 800, 480);
            g2D.setFont(g2D.getFont().deriveFont(32f));
            g2D.drawString("dayCounter: " + dayCounter, 650, 520);
            g2D.drawString("filterAlpha: " + filterAlpha, 650, 560);
        }
    }
}
