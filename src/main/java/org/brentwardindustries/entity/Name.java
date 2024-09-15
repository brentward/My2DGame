package org.brentwardindustries.entity;

public enum Name {
    BOOTS("Boots"),
    CHEST("Chest"),
    DOOR("Door"),
    HEART("Heart"),
    KEY("Key"),
    GREEN_SLIME("Green Slime");

    private final String friendlyName;

    Name(String value) {
        friendlyName = value;
    }

    public String toString() {
        return friendlyName;
    }
}
