package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.object.HeartObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2D;
    Font maruMonica, purisoBold;
    BufferedImage heartFull, heartHalf, heartBlank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/maru_monica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/font/purisa_bold.ttf");
            purisoBold = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new HeartObject(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(maruMonica);
//        g2D.setFont(purisoBold);
//        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2D.drawImage(heartBlank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2D.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2D.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 96f));
        String text = "Blue Boy Adventure";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW
        g2D.setColor(Color.GRAY);
        g2D.drawString(text, x + 5, y + 5);
        // MAIN COLOR
        g2D.setColor(Color.WHITE);
        g2D.drawString(text, x, y);

        // BLUE BOY IMAGE
        x = gp.screenWidth / 2 - gp.tileSize;
        y += gp.tileSize * 2;
        g2D.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 48f));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2D.drawString(text, x, y);
        if (commandNum == 0) {
            g2D.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1;
        g2D.drawString(text, x, y);
        if (commandNum == 1) {
            g2D.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1;
        g2D.drawString(text, x, y);
        if (commandNum == 2) {
            g2D.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2D.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 211);
        g2D.setColor(color);
        g2D.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2D.setColor(color);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXForCenteredText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
