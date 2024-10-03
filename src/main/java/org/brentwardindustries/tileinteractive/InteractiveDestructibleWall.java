package org.brentwardindustries.tileinteractive;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class InteractiveDestructibleWall extends InteractiveTile{
    GamePanel gp;

    public InteractiveDestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon.type == typePickaxe;
    }
    public void playSE() {
        gp.playSE(11);
    }

//    public InteractiveTile getDestroyedForm() {
//        return null;
//    }

    public Color getParticleColor() {
        return new Color(65, 65, 65);
    }

    public int getParticleSize() {
        return 6;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
    public void draw(Graphics2D g2D) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize
                && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize
                && worldY > gp.player.worldY - gp.player.screenY - gp.tileSize
                && worldY < gp.player.worldY + gp.player.screenY+ gp.tileSize) {


            g2D.drawImage(down1, screenX, screenY, null);
        }
    }

    public void checkDrop() {
        int die = new Random().nextInt(100) + 1;
        if (die < 50) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 50 && die < 75) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 75 && die < 100) {
            dropItem(new MagicCrystalObject(gp));
        }
    }
}
