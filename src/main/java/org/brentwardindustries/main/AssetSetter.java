package org.brentwardindustries.main;

import org.brentwardindustries.entity.NpcMerchant;
import org.brentwardindustries.entity.NpcOldMan;
import org.brentwardindustries.monster.GreenSlimeMonster;
import org.brentwardindustries.monster.OrcMonster;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.ChestObject;
import org.brentwardindustries.object.DoorObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.LanternObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.object.TentObject;
import org.brentwardindustries.tileinteractive.InteractiveDryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        int mapNum = 0;
        int i = 0;
        gp.objects[mapNum][i] = new AxeObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 33;
        gp.objects[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        gp.objects[mapNum][i] = new LanternObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 18;
        gp.objects[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.objects[mapNum][i] = new TentObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 19;
        gp.objects[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.objects[mapNum][i] = new DoorObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 14;
        gp.objects[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        gp.objects[mapNum][i] = new DoorObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 12;
        gp.objects[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new KeyObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 30;
        gp.objects[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new ShieldBlueObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 12;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 9;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new TentObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 17;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 20;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PotionRedObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 16;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 20;
    }

    public void setNpcs() {
        int mapNum = 0;
        int i = 0;
        gp.npcs[mapNum][i] = new NpcOldMan(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 21;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 21;

        mapNum = 1;
        gp.npcs[mapNum][i] = new NpcMerchant(gp);
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
        i++;
        gp.monsters[mapNum][i] = new OrcMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 12;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 33;
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
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 25, 27);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 26, 27);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 27, 28);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 27, 29);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 27, 30);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 27, 31);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 28, 31);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDryTree(gp, 29, 31);
    }
 }
