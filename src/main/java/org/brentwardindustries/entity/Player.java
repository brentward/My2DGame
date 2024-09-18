package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.main.KeyHandler;
import org.brentwardindustries.object.FireballObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.RockObject;
import org.brentwardindustries.object.ShieldWoodObject;
import org.brentwardindustries.object.SwordNormalObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

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

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
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
        maxMagic = 4;
        magic = maxMagic;
        ammo = 10;
        strength = 1; // More strength is more damage given
        dexterity = 1; // More dexterity is less damage received
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new SwordNormalObject(gp);
        currentShield = new ShieldWoodObject(gp);
        projectile = new FireballObject(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
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
        if (currentWeapon.name == Name.NORMAL_SWORD) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.name == Name.WOODCUTTERS_AXE) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
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

        if (gp.keyHandler.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 0 && projectile.haveResource(this)) {
            // SET PROJECTILE COORDINATES, DIRECTION, AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST (MAGIC, ARROWS, ETC.)
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            gp.projectileList.add(projectile);
            shotAvailableCounter = 30;
            gp.playSE(10);
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter > 0) {
            shotAvailableCounter--;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (magic > maxMagic) {
            magic = maxMagic;
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
            damageMonster(monsterIndex, attack);

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
            // PICKUP ONLY ITEMS
            if (gp.objects[i].type == typePickupOnly) {
                gp.objects[i].use(this);
                gp.objects[i] = null;
            } else {
                // INVENTORY ITEMS
                String text;
                if (inventory.size() != maxInventorySize) {
                    inventory.add(gp.objects[i]);
                    gp.playSE(1);
                    text = "Picked up a " + gp.objects[i].name.toString() + "!";
                } else {
                    text = "You are overburdened!";
                }
                gp.ui.addMessage(text);
                gp.objects[i] = null;
            }
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
            if (!gp.player.invincible && !gp.monsters[i].dying) {
                gp.playSE(6);

                int damage = gp.monsters[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (!gp.monsters[i].invincible) {
                gp.playSE(5);

                int damage = attack - gp.monsters[i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monsters[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monsters[i].invincible = true;
                gp.monsters[i].damageReaction();

                if (gp.monsters[i].life <= 0) {
                    gp.monsters[i].dying = true;
                    gp.ui.addMessage("Killed the "+ gp.monsters[i].name.toString() + "!");
                    gp.ui.addMessage("Exp + "+ gp.monsters[i].exp);
                    exp += gp.monsters[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 3;
            maxLife += 2;
            life += 2;
            maxMagic += 1;
            magic += 1;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogState;

            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexInSlot();

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == typeWeapon) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == typeShield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == typeConsumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

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
