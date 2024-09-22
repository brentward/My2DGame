package org.brentwardindustries.main;

import org.brentwardindustries.entity.NpcOldMan;
import org.brentwardindustries.monster.GreenSlimeMonster;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.MagicCrystalObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.tileinteractive.InteractiveDryTree;
import org.brentwardindustries.tileinteractive.InteractiveTile;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        int mapNum = 0;
        int i = 0;
        gp.objects[mapNum][i] = new CoinBronzeObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 25;
        gp.objects[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.objects[mapNum][i] = new CoinBronzeObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 21;
        gp.objects[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.objects[mapNum][i] = new CoinBronzeObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 26;
        gp.objects[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[mapNum][i] = new AxeObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 33;
        gp.objects[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        gp.objects[mapNum][i] = new ShieldBlueObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 35;
        gp.objects[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[mapNum][i] = new PotionRedObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 22;
        gp.objects[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.objects[mapNum][i] = new HeartObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 22;
        gp.objects[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.objects[mapNum][i] = new MagicCrystalObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 22;
        gp.objects[mapNum][i].worldY = gp.tileSize * 33;
        i++;
    }

    public void setNpcs() {
        int mapNum = 0;
        int i = 0;
        gp.npcs[mapNum][i] = new NpcOldMan(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 21;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 21;

        mapNum = 1;
        i = 0;
        gp.npcs[mapNum][i] = new NpcOldMan(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 12;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setMonsters() {
        int mapNum = 0;
        int i = 0;
        gp.monsters[mapNum][i] = new GreenSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 23;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        gp.monsters[mapNum][i] = new GreenSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 23;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[mapNum][i] = new GreenSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 24;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monsters[mapNum][i] = new GreenSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 34;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[mapNum][i] = new GreenSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 38;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 42;
    }

    public void setInteractiveTiles() {
        int mapNum = 0;
        int i = 0;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 27, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 28, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 29, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 30, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 31, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 32, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 33, 12);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 31, 21);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 18, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 17, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 16, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 15, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 14, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 13, 40);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 13, 41);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 12, 41);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 11, 41);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 10, 41);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 10, 40);
        i++;
    }
 }
