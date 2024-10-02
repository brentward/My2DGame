package org.brentwardindustries.tile;

import org.brentwardindustries.ai.Node;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    boolean drawPath = false;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        // READ TILE DATA FILE
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // GET TILE NAMES AND COLLISION INFO FROM THE FILE
        String line;
        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tile = new Tile[fileNames.size()];
        getTileImage();

        // GET THE maxWorldCol
        is = getClass().getResourceAsStream("/maps/worldmap.txt");
        br = new BufferedReader(new InputStreamReader(is));


        try {
            line = br.readLine();
            String[] maxTile = line.split(" ");
            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        loadMap("/maps/worldmap.txt", 0);
        loadMap("/maps/indoor01.txt", 1);
        loadMap("/maps/dungeon01.txt", 2);
        loadMap("/maps/dungeon02.txt", 3);
    }

    public void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision;
            fileName = fileNames.get(i);
            collision = collisionStatus.get(i).equals("true");
            setup(i, fileName, collision);
        }
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"
                    + imageName));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize
                    && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize
                    && worldY > gp.player.worldY - gp.player.screenY - gp.tileSize
                    && worldY < gp.player.worldY + gp.player.screenY+ gp.tileSize) {
                g2D.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }
        if (gp.keyHandler.showPaths) {
            g2D.setColor(new Color(255, 0, 0, 70));

            for (Node pathNode : gp.pathFinder.pathList) {
                int worldX = pathNode.col * gp.tileSize;
                int worldY = pathNode.row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2D.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}
