package edu.uoc.pacman.model.utils;

import java.io.Serializable;
import java.lang.constant.Constable;

public enum Direction implements Serializable, Comparable<Direction>, Constable{
    //Enumeration
    RIGHT(1,0,22),
    DOWN(0,1,20),
    LEFT(-1,0,21),
    UP(0,-1,19);

    //Attributes
    private final int keyCode;
    private final int x;
    private final int y;

    //Constructor
    Direction(int x, int y, int keyCode) {
        this.x = x;
        this.y = y;
        this.keyCode = keyCode;
    }

    //Methods

    public static Direction getDirectionByKeyCode(int KeyCode) {
        return switch (KeyCode) {
            case 22 -> Direction.RIGHT;
            case 20 -> Direction.DOWN;
            case 21 -> Direction.LEFT;
            case 19 -> Direction.UP;
            default -> null;
        };
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction opposite() {
        return switch (this.getKeyCode()) {
            case 22 -> Direction.LEFT;
            case 20 -> Direction.UP;
            case 21 -> Direction.RIGHT;
            case 19 -> Direction.DOWN;
            default -> null;
        };
    }

}
