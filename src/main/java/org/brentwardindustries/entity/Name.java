package org.brentwardindustries.entity;

public enum Name {
    BOOTS("Boots"),
    CHEST("Chest"),
    DOOR("Door"),
    HEART("Heart"),
    KEY("Key"),
    GREEN_SLIME("Green Slime"),
    NORMAL_SWORD("Normal Sword"),
    WOOD_SHIELD("Wood Shield"),
    WOODCUTTERS_AXE("Woodcutter's Axe"),
    BLUE_SHIELD("Blue Shield"),
    RED_POTION("Red Potion"),
    FIREBALL("Fireball"),
    ROCK("Rock"),
    MAGIC_CRYSTAL("Magic Crystal");

    private final String friendlyName;

    Name(String value) {
        friendlyName = value;
    }

    public String toString() {
        return friendlyName;
    }
}
