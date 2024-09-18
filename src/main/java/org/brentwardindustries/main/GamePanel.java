package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Player;
import org.brentwardindustries.tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    ControllerHandler controllerHandler = new ControllerHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECTS
    public Player player  = new Player(this, keyHandler);
    public Entity[] objects = new Entity[20];
    public Entity[] npcs = new Entity[10];
    public Entity[] monsters = new Entity[20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();


    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int characterState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObjects();
        assetSetter.setNpcs();
        assetSetter.setMonsters();
//        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / (double) FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
//        long timer = 0;
//        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (double) (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
//                drawCount++;
            }

//            if (timer >= 1_000_000_000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public void update() {
        pollController();
        if (gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for (int i = 0; i < npcs.length; i++) {
                if (npcs[i] != null) {
                    npcs[i].update();
                }
            }
            // MONSTERS
            for (int i = 0; i < monsters.length; i ++) {
                if (monsters[i] != null) {
                    if (monsters[i].alive && !monsters[i].dying) {
                        monsters[i].update();
                    }
                    if (!monsters[i].alive) {
                        monsters[i].checkDrop();
                        monsters[i] = null;
                    }
                }
            }
            // PROJECTILES
            for (int i = 0; i < projectileList.size(); i ++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    public void pollController() {
        controllerHandler.pollController();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyHandler.showDebugText) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2D);
        } else {

            // TILE
            tileManager.draw(g2D);

            entityList.add(player);
            for (Entity npc : npcs) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }
            for (Entity monster : monsters) {
                if (monster != null) {
                    entityList.add(monster);
                }
            }
            for (Entity object : objects) {
                if (object != null) {
                    entityList.add(object);
                }
            }
            for (Entity projectile : projectileList) {
                if (projectile != null) {
                    entityList.add(projectile);
                }
            }

            // SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            // DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2D);
            }
            // EMPTY ENTITY LIST (reversed to fix potential bug?)
            entityList.clear();
//            for (int i = entityList.size(); i > 0; i--) {
//                entityList.remove(i - 1);
//            }
            // EMPTY ENTITY LIST (original)
//            for (int i = 0; i < entityList.size(); i++) {
//                entityList.remove(i);
//            }


            // UI
            ui.draw(g2D);
        }

        // DEBUG
        if (keyHandler.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2D.setFont(new Font("Arial", Font.PLAIN, 20));
            g2D.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            g2D.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2D.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            g2D.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2D.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;
            g2D.drawString("Draw Time: " + passed, x, y);
//            System.out.println("Draw Time: " + passed);
        }

        g2D.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.playLoop();
    }

    public void resumeMusic() {
        music.playLoop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
