package org.brentwardindustries.main;

import org.brentwardindustries.object.ObjKey;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial40, arial80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80B = new Font("Arial", Font.BOLD, 80);
        ObjKey key = new ObjKey();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength, textHeight;
            int x, y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Your time is: " + decimalFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 4;
            g2.drawString(text, x, y);

            g2.setFont(arial80B);
            g2.setColor(Color.YELLOW);

            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 2;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            playTime += (double) 1 / 60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize * 11, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 3);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
