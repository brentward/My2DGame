package org.brentwardindustries.main;

import org.brentwardindustries.entity.NpcOldMan;
import org.brentwardindustries.object.ObjDoor;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new ObjDoor(gp);
        gp.objects[0].worldX = gp.tileSize * 21;
        gp.objects[0].worldY = gp.tileSize * 22;

        gp.objects[1] = new ObjDoor(gp);
        gp.objects[1].worldX = gp.tileSize * 23;
        gp.objects[1].worldY = gp.tileSize * 25;
    }

    public void setNpc() {
        gp.npcs[0] = new NpcOldMan(gp);
        gp.npcs[0].worldX = gp.tileSize * 21;
        gp.npcs[0].worldY = gp.tileSize * 21;

        gp.npcs[1] = new NpcOldMan(gp);
        gp.npcs[1].worldX = gp.tileSize * 11;
        gp.npcs[1].worldY = gp.tileSize * 21;

        gp.npcs[2] = new NpcOldMan(gp);
        gp.npcs[2].worldX = gp.tileSize * 31;
        gp.npcs[2].worldY = gp.tileSize * 21;

        gp.npcs[3] = new NpcOldMan(gp);
        gp.npcs[3].worldX = gp.tileSize * 21;
        gp.npcs[3].worldY = gp.tileSize * 11;

        gp.npcs[4] = new NpcOldMan(gp);
        gp.npcs[4].worldX = gp.tileSize * 21;
        gp.npcs[4].worldY = gp.tileSize * 31;
    }
}
