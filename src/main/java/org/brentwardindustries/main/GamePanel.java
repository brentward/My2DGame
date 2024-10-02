package org.brentwardindustries.main;

import org.brentwardindustries.ai.PathFinder;
import org.brentwardindustries.data.SaveLoad;
import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.Player;
import org.brentwardindustries.environment.EnvironmentManager;
import org.brentwardindustries.tile.Map;
import org.brentwardindustries.tile.TileManager;
import org.brentwardindustries.tileinteractive.InteractiveTile;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int halfTileSize = tileSize / 2;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;

    // FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2D;
    public boolean fullScreenOn = false;

    // FPS
    int FPS = 60;

    // SYSTEM
    String os = System.getProperty("os.name");
    public String cursor = ">";
    public TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    ControllerHandler controllerHandler = new ControllerHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pathFinder = new PathFinder(this);
    EnvironmentManager environmentManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator entityGenerator = new EntityGenerator(this);
    Thread gameThread;

    // ENTITY AND OBJECTS
    public Player player  = new Player(this, keyHandler);
    public Entity[][] objects = new Entity[maxMap][20];
    public Entity[][] npcs = new Entity[maxMap][10];
    public Entity[][] monsters = new Entity[maxMap][20];
    public InteractiveTile[][] interactiveTiles = new InteractiveTile[maxMap][50];
    public Entity[][] projectiles = new Entity[maxMap][50];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();


    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;

    // AREA STATE
    public int currentArea;
    public int nextArea;
    public final int outsideArea = 50;
    public final int indoorArea = 51;
    public final int dungeonArea = 52;

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
        assetSetter.setInteractiveTiles();
        environmentManager.setup();
//        playMusic(0);
        gameState = titleState;
        currentArea = outsideArea;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        g2D = (Graphics2D) tempScreen.getGraphics();

        if (fullScreenOn) {
            setFullScreen();
        }
    }

    public void resetGame(boolean restart){
        clearProjectiles();
        particleList.clear();
        environmentManager.lighting.resetDay();
        player.setDefaultPositions();
        player.resetStatus();
        player.resetCounters();
        assetSetter.resetNpcs();
        assetSetter.resetMonsters();

        if (restart) {
            player.setDefaultValues();
            assetSetter.resetObjects();
            assetSetter.resetInteractiveTiles();
        }
    }

    public void clearObjects() {
        for (Entity[] object : objects) {
            Arrays.fill(object, null);
        }
    }

    public void clearNpcs() {
        for (Entity[] npc : npcs) {
            Arrays.fill(npc, null);
        }
    }

    public void clearMonsters() {
        for (Entity[] monster : monsters) {
            Arrays.fill(monster, null);
        }
    }

    public void clearInteractiveTiles() {
        for (InteractiveTile[] interactiveTile : interactiveTiles) {
            Arrays.fill(interactiveTile, null);
        }
    }

    public void clearProjectiles() {
        for (Entity[] projectile : projectiles) {
            Arrays.fill(projectile, null);
        }
    }

    public void setFullScreen() {
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        if (os.equals("Mac OS X")) {
            enableOSXFullscreen(Main.window);
            requestOSXFullscreen(Main.window);
        }
        graphicsDevice.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public static void enableOSXFullscreen(Window window) {
        try {
            Class<?> util = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class[] params = new Class[]{Window.class, Boolean.TYPE};
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void requestOSXFullscreen(Window window) {
        try {
            Class<?> appClass = Class.forName("com.apple.eawt.Application");
            Class[] params = new Class[]{};

            Method getApplication = appClass.getMethod("getApplication", params);
            Object application = getApplication.invoke(appClass);
            Method requestToggleFulLScreen = application.getClass().getMethod("requestToggleFullScreen",
                    Window.class);

            requestToggleFulLScreen.invoke(application, window);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
                 | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
                drawToTempScreen();
                drawToScreen();
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
            for (int i = 0; i < npcs[currentMap].length; i++) {
                if (npcs[currentMap][i] != null) {
                    npcs[currentMap][i].update();
                }
            }
            // MONSTERS
            for (int i = 0; i < monsters[currentMap].length; i ++) {
                if (monsters[currentMap][i] != null) {
                    if (monsters[currentMap][i].alive && !monsters[currentMap][i].dying) {
                        monsters[currentMap][i].update();
                    }
                    if (!monsters[currentMap][i].alive) {
                        monsters[currentMap][i].checkDrop();
                        monsters[currentMap][i] = null;
                    }
                }
            }
            // PROJECTILES
            for (int i = 0; i < projectiles[currentMap].length; i ++) {
                if (projectiles[currentMap][i] != null) {
                    if (projectiles[currentMap][i].alive) {
                        projectiles[currentMap][i].update();
                    }
                    if (!projectiles[currentMap][i].alive) {
                        projectiles[currentMap][i] = null;
                    }
                }
            }
            // PARTICLES
            for (int i = 0; i < particleList.size(); i ++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).alive) {
                        particleList.remove(i--);
                    }
                }
            }
            for (InteractiveTile interactiveTile : interactiveTiles[currentMap]) {
                if (interactiveTile != null) {
                    interactiveTile.update();
                }
            }
            environmentManager.update();
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    public void pollController() {
        controllerHandler.pollController();
    }

    public void drawToTempScreen() {
        // DEBUG
        long drawStart = 0;
        if (keyHandler.showDebugText) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2D);
        } else if (gameState == mapState) {
            map.drawFullMapScreen(g2D);
        } else {

            // TILE
            tileManager.draw(g2D);

            for (InteractiveTile interactiveTile : interactiveTiles[currentMap]) {
                if (interactiveTile != null) {
                    interactiveTile.draw(g2D);
                }
            }

            entityList.add(player);
            for (Entity npc : npcs[currentMap]) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }
            for (Entity monster : monsters[currentMap]) {
                if (monster != null) {
                    entityList.add(monster);
                }
            }
            for (Entity object : objects[currentMap]) {
                if (object != null) {
                    entityList.add(object);
                }
            }
            for (Entity projectile : projectiles[currentMap]) {
                if (projectile != null) {
                    entityList.add(projectile);
                }
            }
            for (Entity particle : particleList) {
                if (particle != null) {
                    entityList.add(particle);
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
            entityList.clear();

            // ENVIRONMENT
            environmentManager.draw(g2D);

            // DRAW MINI MAP
            map.drawMiniMap(g2D);

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
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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

    public  void endMusic() {
        music.end();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public void changeArea() {
        if (nextArea != currentArea) {
            stopMusic();
            if (nextArea == outsideArea) {
                playMusic(0);
            }
            if (nextArea == indoorArea) {
                playMusic(18);
            }
            if (nextArea == dungeonArea) {
                playMusic(19);
            }
        }
        currentArea = nextArea;
        assetSetter.resetMonsters();
    }
}
