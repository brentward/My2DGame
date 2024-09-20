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
        int i = 0;
        gp.objects[i] = new CoinBronzeObject(gp);
        gp.objects[i].worldX = gp.tileSize * 25;
        gp.objects[i].worldY = gp.tileSize * 23;
        i++;
        gp.objects[i] = new CoinBronzeObject(gp);
        gp.objects[i].worldX = gp.tileSize * 21;
        gp.objects[i].worldY = gp.tileSize * 19;
        i++;
        gp.objects[i] = new CoinBronzeObject(gp);
        gp.objects[i].worldX = gp.tileSize * 26;
        gp.objects[i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[i] = new AxeObject(gp);
        gp.objects[i].worldX = gp.tileSize * 33;
        gp.objects[i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[i] = new ShieldBlueObject(gp);
        gp.objects[i].worldX = gp.tileSize * 35;
        gp.objects[i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[i] = new PotionRedObject(gp);
        gp.objects[i].worldX = gp.tileSize * 22;
        gp.objects[i].worldY = gp.tileSize * 27;
        i++;
        gp.objects[i] = new HeartObject(gp);
        gp.objects[i].worldX = gp.tileSize * 22;
        gp.objects[i].worldY = gp.tileSize * 19;
        i++;
        gp.objects[i] = new MagicCrystalObject(gp);
        gp.objects[i].worldX = gp.tileSize * 22;
        gp.objects[i].worldY = gp.tileSize * 33;
        i++;
    }

    public void setNpcs() {
        gp.npcs[0] = new NpcOldMan(gp);
        gp.npcs[0].worldX = gp.tileSize * 21;
        gp.npcs[0].worldY = gp.tileSize * 21;
    }

    public void setMonsters() {

        int i = 0;
        gp.monsters[i] = new GreenSlimeMonster(gp);
        gp.monsters[i].worldX = gp.tileSize * 23;
        gp.monsters[i].worldY = gp.tileSize * 38;
        i++;
        gp.monsters[i] = new GreenSlimeMonster(gp);
        gp.monsters[i].worldX = gp.tileSize * 23;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new GreenSlimeMonster(gp);
        gp.monsters[i].worldX = gp.tileSize * 24;
        gp.monsters[i].worldY = gp.tileSize * 37;
        i++;
        gp.monsters[i] = new GreenSlimeMonster(gp);
        gp.monsters[i].worldX = gp.tileSize * 34;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new GreenSlimeMonster(gp);
        gp.monsters[i].worldX = gp.tileSize * 38;
        gp.monsters[i].worldY = gp.tileSize * 42;
    }

    public void setInteractiveTiles() {
        int i = 0;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 27, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 28, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 29, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 30, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 31, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 32, 12);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 33, 12);
        i++;
//        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 30, 20);
//        i++;
//        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 30, 21);
//        i++;
//        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 30, 22);
//        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 20, 20);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 20, 21);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 20, 22);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 22, 24);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 23, 24);
        i++;
        gp.interactiveTiles[i] = new InteractiveDryTree(gp, 24, 24);
        i++;
    }
 }
