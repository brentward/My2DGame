package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.main.KeyHandler;
import org.brentwardindustries.object.*;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - gp.halfTileSize;
        screenY = gp.screenHeight / 2 - gp.halfTileSize;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = Direction.DOWN;

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMagic = 4;
        invincible = false;
        transparent = false;
        attacking = false;
        magic = maxMagic;
        ammo = 10;
        strength = 1; // More strength is more damage given
        dexterity = 1; // More dexterity is less damage received
        exp = 0;
        nextLevelExp = 5;
        coin = 400;
        currentWeapon = new SwordNormalObject(gp);
        currentShield = new ShieldWoodObject(gp);
        projectile = new FireballObject(gp);
        setAttack();
        setDefense();
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = Direction.DOWN;
    }

    public void restoreLifeAndMagic() {
        life = maxLife;
        magic = maxMagic;
        invincible = false;
        transparent = false;
        attacking = false;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new AxeObject(gp));
        inventory.add(new KeyObject(gp));
        inventory.add(new SwordMagicObject(gp));
        inventory.get(3).amount = 10;
    }

    public void setAttack() {
        attackArea = currentWeapon.attackArea;
        attackWindupDuration = currentWeapon.attackWindupDuration;
        attackDuration = currentWeapon.attackDuration;
        attack = strength * currentWeapon.attackValue;
    }

    public void setDefense() {
        defense = dexterity * currentShield.defenseValue;
    }

    public void getImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getSleepImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void getAttackImage() {
        if (currentWeapon.name == Name.NORMAL_SWORD || currentWeapon.name == Name.MAGIC_SWORD) {
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

    public void getGuardImage() {
        guardUp = setup("/player/boy_guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/player/boy_guard_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/boy_guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/player/boy_guard_right", gp.tileSize, gp.tileSize);
    }

    public void update() {
        if (knockBack) {
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            gp.collisionChecker.checkObject(this, true);

            // CHECK NPC COLLISION
            gp.collisionChecker.checkEntity(this, gp.npcs);

            // CHECK MONSTER COLLISION
            gp.collisionChecker.checkEntity(this, gp.monsters);

            // CHECK INTERACTIVE TILE COLLISION
            gp.collisionChecker.checkEntity(this, gp.interactiveTiles);

            if (collisionOn) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            } else {
                switch (knockBackDirection) {
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
        } else if (attacking) {
            attacking();
        } else if (keyHandler.guardPressed) {
            guarding = true;
            guardCounter++;
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

            // CHECK INTERACTIVE TILE COLLISION
            gp.collisionChecker.checkEntity(this, gp.interactiveTiles);

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
            guarding = false;
            guardCounter = 0;

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
            guarding = false;
            guardCounter = 0;
        }

        if (gp.keyHandler.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 30 && projectile.haveResource(this)) {
            // SET PROJECTILE COORDINATES, DIRECTION, AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST (MAGIC, ARROWS, ETC.)
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            for (int i = 0; i < gp.projectiles[1].length; i++) {
                if (gp.projectiles[gp.currentMap][i] == null) {
                    gp.projectiles[gp.currentMap][i] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
            gp.playSE(10);
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (magic > maxMagic) {
            magic = maxMagic;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(12);
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // PICKUP ONLY ITEMS
            if (gp.objects[gp.currentMap][i].type == typePickupOnly) {
                gp.objects[gp.currentMap][i].use(this);
                gp.objects[gp.currentMap][i] = null;
            } else if (gp.objects[gp.currentMap][i].type == typeObstacle) {
                if (keyHandler.enterPressed && gp.objects[gp.currentMap][i].alive) {
                    attackCanceled = true;
                    gp.objects[gp.currentMap][i].interact();
            }
            } else {
                // INVENTORY ITEMS
                String text;
                if (canObtainItem(gp.objects[gp.currentMap][i])) {
                    gp.playSE(1);
                    text = "Picked up a " + gp.objects[gp.currentMap][i].name.toString() + "!";
                } else {
                    text = "You are overburdened!";
                }
                gp.ui.addMessage(text);
                gp.objects[gp.currentMap][i] = null;
            }
        }
    }

    public void interactNpc(int i) {
        if (gp.keyHandler.enterPressed) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogState;
                gp.npcs[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!gp.player.invincible && !gp.monsters[gp.currentMap][i].dying) {
                gp.playSE(6);

                int damage = gp.monsters[gp.currentMap][i].attack - defense;
                if (damage < 1) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {
            if (!gp.monsters[gp.currentMap][i].invincible) {
                gp.playSE(5);

                if (knockBackPower > 0) {
                    setKnockBack(gp.monsters[gp.currentMap][i], attacker, knockBackPower);
                }

                if (gp.monsters[gp.currentMap][i].offBalance) {
                    attack *= 5;
                }
                int damage = attack - gp.monsters[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monsters[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monsters[gp.currentMap][i].invincible = true;
                gp.monsters[gp.currentMap][i].damageReaction();

                if (gp.monsters[gp.currentMap][i].life <= 0) {
                    gp.monsters[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the "+ gp.monsters[gp.currentMap][i].name.toString() + "!");
                    gp.ui.addMessage("Exp + "+ gp.monsters[gp.currentMap][i].exp);
                    exp += gp.monsters[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.interactiveTiles[gp.currentMap][i].destructible
                && gp.interactiveTiles[gp.currentMap][i].isCorrectItem(this)
                && !gp.interactiveTiles[gp.currentMap][i].invincible) {
            gp.interactiveTiles[gp.currentMap][i].playSE();
            gp.interactiveTiles[gp.currentMap][i].life--;
            gp.interactiveTiles[gp.currentMap][i].invincible = true;

            // GENERATE PARTICLE
            generateParticle(gp.interactiveTiles[gp.currentMap][i], gp.interactiveTiles[gp.currentMap][i]);

            if (gp.interactiveTiles[gp.currentMap][i].life == 0) {
                gp.interactiveTiles[gp.currentMap][i] = gp.interactiveTiles[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectiles[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
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
            setAttack();
            setDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogState;

            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexInSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == typeSword || selectedItem.type == typeAxe) {
                currentWeapon = selectedItem;
                setAttack();
                getAttackImage();
            }
            if (selectedItem.type == typeShield) {
                currentShield = selectedItem;
                setDefense();
            }
            if (selectedItem.type == typeLight) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == typeConsumable) {
                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    public int searchItemInInventory(Name itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name == itemName) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        if (item.stackable) {
            int index = searchItemInInventory(item.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
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
                        g2D.setStroke(new BasicStroke(1));
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y
                                - attackArea.height, attackArea.width, attackArea.height);
                    }
                }
                if (guarding) {
                    image = guardUp;
                }
            }
            case DOWN -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? down1 : down2;
                }
                if (attacking) {
                    image = (spriteNum == 1) ? attackDown1 : attackDown2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setStroke(new BasicStroke(1));
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y
                                + solidArea.height, attackArea.width, attackArea.height);
                    }
                }
                if (guarding) {
                    image = guardDown;
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
                        g2D.setStroke(new BasicStroke(1));
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x - attackArea.width, screenY
                                + solidArea.y, attackArea.width, attackArea.height);
                    }
                }
                if (guarding) {
                    image = guardLeft;
                }
            }
            case RIGHT -> {
                if (!attacking) {
                    image = (spriteNum == 1) ? right1 : right2;
                }
                if (attacking) {
                    image = (spriteNum == 1) ? attackRight1 : attackRight2;
                    if (gp.keyHandler.showHitBox) {
                        g2D.setStroke(new BasicStroke(1));
                        g2D.setColor(Color.PINK);
                        g2D.drawRect(screenX + solidArea.x + solidArea.width, screenY
                                + solidArea.y, attackArea.width, attackArea.height);
                    }
                }
                if (guarding) {
                    image = guardRight;
                }
            }
        }

        if (transparent) {
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2D.drawImage(image, tempScreenX, tempScreenY, null);


        // Reset alpha
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        if (gp.keyHandler.showHitBox) {
            g2D.setStroke(new BasicStroke(1));
            g2D.setColor(Color.YELLOW);
            g2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
