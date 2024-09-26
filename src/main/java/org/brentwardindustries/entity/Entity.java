package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2,
            attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    String[] dialogues = new String[20];

    // STATE
    public int worldX, worldY;
    public Direction direction = Direction.DOWN;
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collision = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;

    // CHARACTER ATTRIBUTES
    public Name name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMagic;
    public int magic;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    // ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // TYPE
    public int type;
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;
    public final int typeSword = 3;
    public final int typeAxe = 4;
    public final int typeShield = 5;
    public final int typeConsumable = 6;
    public final int typePickupOnly = 7;
    public final int typeObstacle = 8;
    public final int typeLight = 9;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return getLeftX() + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return getTopY() + solidArea.height;
    }

    public int getCol() {
        return getLeftX() / gp.tileSize;
    }

    public int getRow() {
        return getTopY() / gp.tileSize;
    }

    public void setAction() {}

    public void damageReaction() {}

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case UP -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.UP;
            case LEFT -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.LEFT;
        }
    }

    public void interact() {}

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {}

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.objects[1].length; i++) {
            if (gp.objects[gp.currentMap][i] == null) {
                gp.objects[gp.currentMap][i] = droppedItem;
                gp.objects[gp.currentMap][i].worldX = worldX;
                gp.objects[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle particle1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle particle2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle particle3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle particle4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(particle1);
        gp.particleList.add(particle2);
        gp.particleList.add(particle3);
        gp.particleList.add(particle4);
    }

    private void checkCollision() {
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npcs);
        gp.collisionChecker.checkEntity(this, gp.monsters);
        gp.collisionChecker.checkEntity(this, gp.interactiveTiles);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == typeMonster && contactPlayer) {
            damagePlayer(attack);
        }
    }

    public void update() {
        if (knockBack) {
            checkCollision();
            if (collisionOn) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            } else {
//                switch (gp.player.direction) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }
            knockBackCounter++;
            if (knockBackCounter > 10) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }
        } else {
            setAction();
            checkCollision();

            if (!collisionOn) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }
        }

        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack) {
        if (!gp.player.invincible) {
            gp.playSE(6);
            int damage = attack - gp.player.defense;
            if (damage < 1) {
                damage = 1;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }

    }

    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize
                && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize
                && worldY > gp.player.worldY - gp.player.screenY - gp.tileSize
                && worldY < gp.player.worldY + gp.player.screenY+ gp.tileSize) {

            switch (direction) {
                case UP -> image = (spriteNum == 1) ? up1 : up2;
                case DOWN -> image = (spriteNum == 1) ? down1 : down2;
                case LEFT -> image = (spriteNum == 1) ? left1 : left2;
                case RIGHT -> image = (spriteNum == 1) ? right1 : right2;
            }

            // Monster HP bar
            if (type == typeMonster && hpBarOn) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2D.setColor(new Color(35, 35, 35));
                g2D.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2D.setColor(new Color(255, 0, 30));
                g2D.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2D, 0.4f);
            }
            if (dying) {
                dyingAnimation(g2D);
            }

            g2D.drawImage(image, screenX, screenY, null);

            // Reset alpha
            changeAlpha(g2D, 1f);

            // DEBUG
            if (gp.keyHandler.showHitBox) {
                g2D.setColor(Color.RED);
                g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
        }
    }

    public void dyingAnimation(Graphics2D g2D) {
        dyingCounter++;

        int flashInterval = 5;

        if (dyingCounter <= flashInterval) {changeAlpha(g2D, 0f);}
        if (dyingCounter > flashInterval && dyingCounter <= flashInterval * 2) {changeAlpha(g2D, 1f);}
        if (dyingCounter > flashInterval * 2 && dyingCounter <= flashInterval * 3) {changeAlpha(g2D, 0f);}
        if (dyingCounter > flashInterval * 3 && dyingCounter <= flashInterval* 4) {changeAlpha(g2D, 1f);}
        if (dyingCounter > flashInterval * 4 && dyingCounter <= flashInterval * 5) {changeAlpha(g2D, 0f);}
        if (dyingCounter > flashInterval * 5 && dyingCounter <= flashInterval * 6) {changeAlpha(g2D, 1f);}
        if (dyingCounter > flashInterval * 6 && dyingCounter <= flashInterval * 7) {changeAlpha(g2D, 0f);}
        if (dyingCounter > flashInterval * 7 && dyingCounter <= flashInterval * 8) {changeAlpha(g2D, 1f);}
        if (dyingCounter > flashInterval * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2D, float alphaValue) {
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
    public void searchPath(int goalCol, int goalRow) {
        int startCol = getCol();
        int startRow = getRow();
        gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pathFinder.search()) {
            // NEXT WORLDX AND WORLDY
            int nextX = gp.pathFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pathFinder.pathList.getFirst().row * gp.tileSize;

            // ENTITY'S SOLIDAREA POSITIONS
            int enLeftX = getLeftX();
            int enRightX = getRightX();
            int enTopY = getTopY();
            int enBottomY = getBottomY();

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = Direction.UP;
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = Direction.DOWN;
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if (enLeftX > nextX) {
                    direction = Direction.LEFT;
                }
                if (enLeftX < nextX) {
                    direction = Direction.RIGHT;
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                direction = Direction.UP;
                checkCollision();
                if (collisionOn) {
                    direction = Direction.LEFT;
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                direction = Direction.UP;
                checkCollision();
                if (collisionOn) {
                    direction = Direction.RIGHT;
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                direction = Direction.DOWN;
                checkCollision();
                if (collisionOn) {
                    direction = Direction.LEFT;
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                direction = Direction.DOWN;
                checkCollision();
                if (collisionOn) {
                    direction = Direction.RIGHT;
                }
            }

//            int nextCol = gp.pathFinder.pathList.getFirst().col;
//            int nextRow = gp.pathFinder.pathList.getFirst().row;
//            if (nextCol == goalCol && nextRow == goalRow) {
//                onPath = false;
//            }
        }
    }

    public int getDetected(Entity user, Entity[][] target, Name tartetName) {
        int index = 999;

        // CHECK SURROUNDING OBJECTS
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case UP -> nextWorldY = user.getTopY() - 1;
            case DOWN -> nextWorldY = user.getBottomY() + 1;
            case LEFT -> nextWorldX = user.getLeftX() - 1;
            case RIGHT -> nextWorldX = user.getRightX() + 1;
        }
        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null
                    && target[gp.currentMap][i].getCol() == col
                    && target[gp.currentMap][i].getRow() == row
                    && target[gp.currentMap][i].name == tartetName) {
                index = i;
                break;
            }
        }
        return index;
    }
}
