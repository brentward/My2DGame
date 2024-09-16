package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.main.KeyHandler;
import org.brentwardindustries.object.ShieldWoodObject;
import org.brentwardindustries.object.SwordNormalObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = Direction.DOWN;

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1; // More strength is more damage given
        dexterity = 1; // More dexterity is less damage received
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new SwordNormalObject(gp);
        currentShield = new ShieldWoodObject(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public int getAttack() {
        return strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
//        up1 = setup("/player/link_up_1");
//        up2 = setup("/player/link_up_2");
//        down1 = setup("/player/link_down_1");
//        down2 = setup("/player/link_down_2");
//        left1 = setup("/player/link_left_1");
//        left2 = setup("/player/link_left_2");
//        right1 = setup("/player/link_right_1");
//        right2 = setup("/player/link_right_2");
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {
        if (attacking) {
            attacking();
        } else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed
                || keyHandler.rightPressed || keyHandler.enterPressed) {
            if (keyHandler.upPressed) {
                direction = Direction.UP;
            }
            if (keyHandler.downPressed) {
                direction = Direction.DOWN;
            }
            if (keyHandler.leftPressed) {
                direction = Direction.LEFT;
            }
            if (keyHandler.rightPressed) {
                direction = Direction.RIGHT;
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npcs);
            interactNpc(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eventHandler.checkEvent();

            if (!collisionOn && !keyHandler.enterPressed) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }

            if (keyHandler.enterPressed && !attackCanceled) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            spriteCounter++;
            if (spriteCounter > 12 && !keyHandler.enterPressed) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            gp.keyHandler.enterPressed = false;

        } else {
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = 1;
                spriteCounter = 0;
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case UP -> worldY -= attackArea.height;
                case DOWN -> worldY += attackArea.height;
                case LEFT -> worldX -= attackArea.width;
                case RIGHT -> worldX += attackArea.width;
            }

            // solidArea set to attackArea
            solidArea.width =  attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            damageMonster(monsterIndex);

            // After checking hit collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
        }
    }

    public void interactNpc(int i) {
        if (gp.keyHandler.enterPressed) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogState;
                gp.npcs[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!gp.player.invincible) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monsters[i].invincible) {
                gp.playSE(5);
                gp.monsters[i].life -= 1;
                gp.monsters[i].invincible = true;
                gp.monsters[i].damageReaction();

                if (gp.monsters[i].life <= 0) {
                    gp.monsters[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        int debugX = 0;
        int debugY = 0;

        switch (direction) {
            case UP -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? up1 : up2;
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    image = (spriteNum == 1) ? attackUp1 : attackUp2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y
                                - attackArea.height, attackArea.width, attackArea.height);
                    }
                }
            }
            case DOWN -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? down1 : down2;
                }
                if (attacking) {
                    image = (spriteNum == 1) ? attackDown1 : attackDown2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y
                                + attackArea.height, attackArea.width, attackArea.height);
                    }
                }
            }
            case LEFT -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? left1 : left2;
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    image = (spriteNum == 1) ? attackLeft1 : attackLeft2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x - attackArea.width, screenY
                                + solidArea.y, attackArea.width, attackArea.height);
                    }
                }
            }
            case RIGHT -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? right1 : right2;
                }
                if (attacking) {
                    image = (spriteNum == 1) ? attackRight1 : attackRight2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x + attackArea.width, screenY
                                + solidArea.y, attackArea.width, attackArea.height);
                    }
                }
            }
        }

        if (invincible) {
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2D.drawImage(image, tempScreenX, tempScreenY, null);


        // Reset alpha
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        if (gp.keyHandler.showHitBox) {
            g2D.setColor(Color.YELLOW);
            g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
//            g2D.setFont(new Font("Arial", Font.PLAIN, 26));
//            g2D.setColor(Color.WHITE);
//            g2D.drawString("Invincible counter: " + invincibleCounter, 10, 400);
        }
    }
}
