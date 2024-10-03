package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.entity.NpcBigRock;
import org.brentwardindustries.entity.NpcMerchant;
import org.brentwardindustries.entity.NpcOldMan;
import org.brentwardindustries.monster.GreenSlimeMonster;
import org.brentwardindustries.monster.OrcMonster;
import org.brentwardindustries.monster.RedSlimeMonster;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.ChestObject;
import org.brentwardindustries.object.DoorIronObject;
import org.brentwardindustries.object.DoorObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.LanternObject;
import org.brentwardindustries.object.PickaxeObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.object.TentObject;
import org.brentwardindustries.tileinteractive.InteractiveDestructibleWall;
import org.brentwardindustries.tileinteractive.InteractiveDryTree;
import org.brentwardindustries.tileinteractive.InteractiveMetalPlate;
import org.brentwardindustries.tileinteractive.InteractiveTile;

import java.util.Arrays;

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
        gp.objects[mapNum][i].setLoot(new TentObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 17;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 20;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PotionRedObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 16;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 20;

        mapNum = 2;
        i = 0;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PickaxeObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 40;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 41;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PotionRedObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 26;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 34;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PotionRedObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 27;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 15;
        i++;
        gp.objects[mapNum][i] = new ChestObject(gp);
        gp.objects[mapNum][i].setLoot(new PotionRedObject(gp));
        gp.objects[mapNum][i].worldX = gp.tileSize * 13;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 16;
        i++;
        gp.objects[mapNum][i] = new DoorIronObject(gp);
        gp.objects[mapNum][i].worldX = gp.tileSize * 18;
        gp.objects[mapNum][i].worldY = gp.tileSize  * 23;
    }

    public void setNpcs() {
        int mapNum = 0;
        int i = 0;
        gp.npcs[mapNum][i] = new NpcOldMan(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 21;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 21;

        mapNum = 1;
//        i = 0;
        gp.npcs[mapNum][i] = new NpcMerchant(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 12;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 7;

        mapNum = 2;
//        i = 0;
        gp.npcs[mapNum][i] = new NpcBigRock(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 20;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 25;
        i++;
        gp.npcs[mapNum][i] = new NpcBigRock(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 11;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.npcs[mapNum][i] = new NpcBigRock(gp);
        gp.npcs[mapNum][i].worldX = gp.tileSize * 23;
        gp.npcs[mapNum][i].worldY = gp.tileSize * 14;
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
        i++;
        gp.monsters[mapNum][i] = new RedSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 37;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.monsters[mapNum][i] = new RedSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 40;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monsters[mapNum][i] = new RedSlimeMonster(gp);
        gp.monsters[mapNum][i].worldX = gp.tileSize * 38;
        gp.monsters[mapNum][i].worldY = gp.tileSize * 11;
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

        mapNum = 2;
        i = 0;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 18, 30);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 17, 31);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 17, 32);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 17, 34);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 18, 34);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 18, 35);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 10, 22);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 10, 24);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 38, 18);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 38, 19);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 38, 20);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 38, 21);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 18, 13);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 18, 14);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 22, 28);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 30, 28);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveDestructibleWall(gp, 32, 28);

        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveMetalPlate(gp, 20, 22);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveMetalPlate(gp, 8, 17);
        i++;
        gp.interactiveTiles[mapNum][i] = new InteractiveMetalPlate(gp, 39, 31);

    }

    public void clearObjects() {
        for (Entity[] objectsList : gp.objects) {
            Arrays.fill(objectsList, null);
        }
    }

    public void clearNpcs() {
        for (Entity[] npcsList : gp.npcs) {
            Arrays.fill(npcsList, null);
        }
    }

    public void clearMonsters() {
        for (Entity[] monstersList : gp.monsters) {
            Arrays.fill(monstersList, null);
        }
    }

    public void clearInteractiveTiles() {
        for (InteractiveTile[] interactiveTilesList : gp.interactiveTiles) {
            Arrays.fill(interactiveTilesList, null);
        }
    }

    public void clearProjectiles() {
        for (Entity[] projectile : gp.projectiles) {
            Arrays.fill(projectile, null);
        }
    }

    public void resetObjects() {
        clearObjects();
        setObjects();
    }

    public void resetNpcs() {
        clearNpcs();
        setNpcs();
    }

    public void resetMonsters() {
        clearMonsters();
        setMonsters();
    }

    public void resetInteractiveTiles() {
        clearInteractiveTiles();
        setInteractiveTiles();
    }
}
