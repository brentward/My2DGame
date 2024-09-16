package org.brentwardindustries.main;

import org.brentwardindustries.entity.NpcOldMan;
import org.brentwardindustries.monster.GreenSlimeMonster;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
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
}
