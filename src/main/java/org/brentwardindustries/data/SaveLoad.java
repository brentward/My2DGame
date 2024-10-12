package org.brentwardindustries.data;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    GamePanel gp;

    public int saveCounter = 0;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            saveCounter = 180;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save.dat"));
            DataStorage dataStorage = new DataStorage();

            dataStorage.level = gp.player.level;
            dataStorage.maxLife = gp.player.maxLife;
            dataStorage.life = gp.player.life;
            dataStorage.maxMagic = gp.player.maxMagic;
            dataStorage.magic = gp.player.magic;
            dataStorage.strength = gp.player.strength;
            dataStorage.dexterity = gp.player.dexterity;
            dataStorage.exp = gp.player.exp;
            dataStorage.nextLevelExp = gp.player.nextLevelExp;
            dataStorage.coin = gp.player.coin;

            for (Entity object : gp.player.inventory) {
                dataStorage.itemNames.add(object.name);
                dataStorage.itemAmounts.add(object.amount);
            }

            dataStorage.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            dataStorage.currentShieldSlot = gp.player.getCurrentShieldSlot();
            dataStorage.currentLightSlot = gp.player.getCurrentLightSlot();

            dataStorage.guardOrcDefeated = Progress.guardOrcDefeated;

            dataStorage.mapObjectNames = new String[gp.maxMap][gp.objects[0].length];
            dataStorage.mapObjectWorldX = new int[gp.maxMap][gp.objects[0].length];
            dataStorage.mapObjectWorldY = new int[gp.maxMap][gp.objects[0].length];
            dataStorage.mapObjectLootNames = new String[gp.maxMap][gp.objects[0].length];
            dataStorage.mapObjectOpened = new boolean[gp.maxMap][gp.objects[0].length];
            dataStorage.interactiveTileNames = new String[gp.maxMap][gp.interactiveTiles[0].length];
            dataStorage.interactiveTileWorldX = new int[gp.maxMap][gp.interactiveTiles[0].length];
            dataStorage.interactiveTileWorldY = new int[gp.maxMap][gp.interactiveTiles[0].length];
            dataStorage.interactiveTileLife = new int[gp.maxMap][gp.interactiveTiles[0].length];

            for (int mapIndex = 0; mapIndex < gp.maxMap; mapIndex++) {
                for (int objectIndex = 0; objectIndex < gp.objects[mapIndex].length; objectIndex++) {
                    if (gp.objects[mapIndex][objectIndex] == null) {
                        dataStorage.mapObjectNames[mapIndex][objectIndex] = "None";
                    } else {
                        dataStorage.mapObjectNames[mapIndex][objectIndex] = gp.objects[mapIndex][objectIndex].name;
                        dataStorage.mapObjectWorldX[mapIndex][objectIndex] = gp.objects[mapIndex][objectIndex].worldX;
                        dataStorage.mapObjectWorldY[mapIndex][objectIndex] = gp.objects[mapIndex][objectIndex].worldY;
                        if (gp.objects[mapIndex][objectIndex].loot != null) {
                            dataStorage.mapObjectLootNames[mapIndex][objectIndex] = gp.objects[mapIndex][objectIndex].loot.name;
                        }
                        dataStorage.mapObjectOpened[mapIndex][objectIndex] = gp.objects[mapIndex][objectIndex].opened;
                    }
                }
                for (int interactiveTileIndex = 0; interactiveTileIndex < gp.interactiveTiles[mapIndex].length; interactiveTileIndex++) {
                    if (gp.interactiveTiles[mapIndex][interactiveTileIndex] == null) {
                        dataStorage.interactiveTileNames[mapIndex][interactiveTileIndex] = "None";
                    } else {
                        dataStorage.interactiveTileNames[mapIndex][interactiveTileIndex] = gp.interactiveTiles[mapIndex][interactiveTileIndex].name;
                        dataStorage.interactiveTileWorldX[mapIndex][interactiveTileIndex] = gp.interactiveTiles[mapIndex][interactiveTileIndex].worldX;
                        dataStorage.interactiveTileWorldY[mapIndex][interactiveTileIndex] = gp.interactiveTiles[mapIndex][interactiveTileIndex].worldY;
                        dataStorage.interactiveTileLife[mapIndex][interactiveTileIndex] = gp.interactiveTiles[mapIndex][interactiveTileIndex].life;
                    }
                }
            }

            objectOutputStream.writeObject(dataStorage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean load() {
        boolean loaded = false;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.dat"));
            DataStorage dataStorage = (DataStorage) objectInputStream.readObject();

            gp.player.level = dataStorage.level;
            gp.player.maxLife = dataStorage.maxLife;
            gp.player.life = dataStorage.life;
            gp.player.maxMagic = dataStorage.maxMagic;
            gp.player.magic = dataStorage.magic;
            gp.player.strength = dataStorage.strength;
            gp.player.dexterity = dataStorage.dexterity;
            gp.player.exp = dataStorage.exp;
            gp.player.nextLevelExp = dataStorage.nextLevelExp;
            gp.player.coin = dataStorage.coin;

            gp.player.inventory.clear();
            for (int i = 0; i < dataStorage.itemNames.size(); i++) {
                Entity object = gp.entityGenerator.getObject(dataStorage.itemNames.get(i));
                object.amount = dataStorage.itemAmounts.get(i);
                gp.player.inventory.add(object);
            }
            if (dataStorage.currentWeaponSlot >= 0) {
                gp.player.currentWeapon = gp.player.inventory.get(dataStorage.currentWeaponSlot);
            }
            if (dataStorage.currentShieldSlot >= 0) {
                gp.player.currentShield = gp.player.inventory.get(dataStorage.currentShieldSlot);
            }
            if (dataStorage.currentLightSlot >= 0) {
                gp.player.currentLight = gp.player.inventory.get(dataStorage.currentLightSlot);
            }
            gp.player.setAttack();
            gp.player.setDefense();
            gp.player.setAttackImage();
            gp.player.setGuardImage();
            gp.player.lightUpdated = true;

            Progress.guardOrcDefeated = dataStorage.guardOrcDefeated;

            for (int mapIndex = 0; mapIndex < dataStorage.mapObjectNames.length; mapIndex++) {
                for (int objectIndex = 0; objectIndex < dataStorage.mapObjectNames[mapIndex].length; objectIndex++) {
                    if (dataStorage.mapObjectNames[mapIndex][objectIndex].equals("None")) {
                        gp.objects[mapIndex][objectIndex] = null;
                    } else {
                        gp.objects[mapIndex][objectIndex] = gp.entityGenerator.getObject(dataStorage.mapObjectNames[mapIndex][objectIndex]);
                        gp.objects[mapIndex][objectIndex].worldX = dataStorage.mapObjectWorldX[mapIndex][objectIndex];
                        gp.objects[mapIndex][objectIndex].worldY = dataStorage.mapObjectWorldY[mapIndex][objectIndex];
                        if (dataStorage.mapObjectLootNames[mapIndex][objectIndex] != null) {
                            gp.objects[mapIndex][objectIndex].setLoot(gp.entityGenerator.getObject(dataStorage.mapObjectLootNames[mapIndex][objectIndex]));
                        }
                        gp.objects[mapIndex][objectIndex].opened = dataStorage.mapObjectOpened[mapIndex][objectIndex];
                        if (gp.objects[mapIndex][objectIndex].opened) {
                            gp.objects[mapIndex][objectIndex].down1 =  gp.objects[mapIndex][objectIndex].image2;
                        }
                    }
                }
                for (int interactiveTileIndex = 0; interactiveTileIndex < gp.interactiveTiles[mapIndex].length; interactiveTileIndex++) {
                    String name = dataStorage.interactiveTileNames[mapIndex][interactiveTileIndex];
                    if (name.equals("None")) {
                        gp.interactiveTiles[mapIndex][interactiveTileIndex] = null;
                    } else {
                        int worldX = dataStorage.interactiveTileWorldX[mapIndex][interactiveTileIndex];
                        int worldY = dataStorage.interactiveTileWorldY[mapIndex][interactiveTileIndex];

                        gp.interactiveTiles[mapIndex][interactiveTileIndex] = gp.entityGenerator.getInteractiveTile(name, worldX, worldY);
                        gp.interactiveTiles[mapIndex][interactiveTileIndex].life = dataStorage.interactiveTileLife[mapIndex][interactiveTileIndex];
                    }
                }
            }
            loaded = true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loaded;
    }
}
