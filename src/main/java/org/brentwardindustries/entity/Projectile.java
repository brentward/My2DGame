package org.brentwardindustries.entity;

import org.brentwardindustries.main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, Direction direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if (user == gp.player) {
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, attack + gp.player.level, knockBackPower);
//                generateParticle(user.projectile, gp.monsters[gp.currentMap][monsterIndex]);
                generateParticle(user.projectile, user.projectile);
                alive = false;
            }
        } else {
            boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
            if (!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, user.projectile);
                alive = false;
            }
        }
        switch (direction) {
            case UP -> worldY -= speed;
            case DOWN -> worldY += speed;
            case LEFT -> worldX -= speed;
            case RIGHT -> worldX += speed;
        }
        life--;
        if (life < 0) {
            alive = false;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    public boolean haveResource(Entity user) {
        return false;
    }

    public void subtractResource(Entity user) {}
}
