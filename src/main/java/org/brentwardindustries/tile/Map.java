package org.brentwardindustries.tile;

import org.brentwardindustries.main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Map extends TileManager{
    GamePanel gp;
    BufferedImage[] worldMap;
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;

        for (int i = 0; i < gp.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g2D = worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;
                g2D.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2D.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2D) {
        // Background color
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw Map
        int width = 500;
        int height = 500;
        int x = (gp.screenWidth / 2) - (width / 2);
        int y = (gp.screenHeight / 2) - (height / 2);
        g2D.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // Draw Player
        double scale = (double) (gp.tileSize * gp.maxWorldCol) / (double) width;
        int playerX = (int) (x + gp.player.worldX / scale);
        int playerWidth = (int) (gp.tileSize / scale);
        scale = (double) (gp.tileSize * gp.maxWorldRow) / (double) height;
        int playerY = (int) (y + gp.player.worldY / scale);
        int playerHeight = (int) (gp.tileSize / scale);
        g2D.drawImage(gp.player.down1, playerX, playerY, playerWidth, playerHeight, null);

        // UI
        g2D.setFont(gp.ui.font.deriveFont(32f));
        g2D.setColor(Color.WHITE);
        g2D.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(Graphics2D g2D) {
        if (miniMapOn) {
            // Draw Map
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - (width + 50) ;
            int y = 50;

            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2D.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

            // Draw Player
            double scale = (double) (gp.tileSize * gp.maxWorldCol) / (double) width;
            int playerX = (int) (x + gp.player.worldX / scale);
            int playerWidth = (int) (gp.tileSize / 3);
            scale = (double) (gp.tileSize * gp.maxWorldRow) / (double) height;
            int playerY = (int) (y + gp.player.worldY / scale);
            int playerHeight = (int) (gp.tileSize / 3);
            g2D.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerWidth, playerHeight, null);

            // Reset alpha
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }
}
