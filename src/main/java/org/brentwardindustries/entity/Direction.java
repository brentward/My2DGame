package org.brentwardindustries.entity;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ANY;

    public Direction opposite() {
        Direction oppositeDirection = Direction.ANY;
        switch (this) {
            case UP -> oppositeDirection = DOWN;
            case DOWN ->  oppositeDirection = UP;
            case LEFT ->  oppositeDirection = RIGHT;
            case RIGHT -> oppositeDirection = LEFT;
        }
        return oppositeDirection;
    }
}
