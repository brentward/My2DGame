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
//        gp.npcs[0] = new NpcOldMan(gp);
//        gp.npcs[0].worldX = gp.tileSize * 9;
//        gp.npcs[0].worldY = gp.tileSize * 10;
    }

    public void setMonsters() {
        gp.monsters[0] = new GreenSlimeMonster(gp);
        gp.monsters[0].worldX = gp.tileSize * 23;
        gp.monsters[0].worldY = gp.tileSize * 36;

        gp.monsters[1] = new GreenSlimeMonster(gp);
        gp.monsters[1].worldX = gp.tileSize * 23;
        gp.monsters[1].worldY = gp.tileSize * 37;
//        gp.monsters[0] = new GreenSlimeMonster(gp);
//        gp.monsters[0].worldX = gp.tileSize * 11;
//        gp.monsters[0].worldY = gp.tileSize * 10;
//
//        gp.monsters[1] = new GreenSlimeMonster(gp);
//        gp.monsters[1].worldX = gp.tileSize * 11;
//        gp.monsters[1].worldY = gp.tileSize * 11;
    }
}
