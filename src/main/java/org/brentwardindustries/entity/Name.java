package org.brentwardindustries.entity;

public enum Name {
    BOOTS("Boots"),
    CHEST("Chest"),
    DOOR("Door"),
    HEART("Heart"),
    KEY("Key"),
    GREEN_SLIME("Green Slime"),
    ORC("Orc"),
    NORMAL_SWORD("Normal Sword"),
    MAGIC_SWORD("Matic Sword"),
    WOOD_SHIELD("Wood Shield"),
    WOODCUTTERS_AXE("Woodcutter's Axe"),
    BLUE_SHIELD("Shield of Blue"),
    RED_POTION("Red Potion"),
    FIREBALL("Fireball"),
    ROCK("Rock"),
    MAGIC_CRYSTAL("Magic Crystal"),
    BRONZE_COIN("Bronze Coin"),
    LANTERN("Lantern"),
    TENT("Tent");

    private final String friendlyName;

    Name(String value) {
        friendlyName = value;
    }

    public String toString() {
        return friendlyName;
    }
}
