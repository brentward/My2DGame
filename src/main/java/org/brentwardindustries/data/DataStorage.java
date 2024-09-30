package org.brentwardindustries.data;

import org.brentwardindustries.entity.Name;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    // Player Stats
    int level;
    int maxLife;
    int life;
    int maxMagic;
    int magic;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    // Player Inventory
    ArrayList<Name> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();

    int currentWeaponSlot;
    int currentShieldSlot;
    int currentLightSlot;

    Name[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    Name[][] mapObjectLootNames;
    boolean[][] mapObjectOpened;

    int[][] interactiveTileLife;
}
