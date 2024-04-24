package edu.uoc.pacman.model.utils;

import java.io.Serializable;
import java.lang.constant.Constable;

public enum Sprite implements Serializable, Comparable<Sprite>, Constable {
    //Enumeration
    BLINKY('B',"images/blinky.png"),
    CLYDE('C',"images/clyde.png"),
    DOT('.',"images/dot.png"),
    ENERGIZER('0',"images/energizer.png"),
    INKY('I',"images/inky.png"),
    LIFE('L',"images/life.png"),
    PACMAN_DOWN('V',"images/pac_down.png"),
    PACMAN_LEFT('>',"images/pac_left.png"),
    PACMAN_RIGHT('<',"images/pac_right.png"),
    PACMAN_UP('^',"images/pac_up.png"),
    PATH(' ',"images/path.png"),
    PINKY('P',"images/pinky.png"),
    WALL('#',"images/wall.png");

    //Attributes
    private final String imageSrc;
    private final char symbol;

    //Constructor
    Sprite(char symbol, String imageSrc) {
        this.imageSrc = imageSrc;
        this.symbol = symbol;
    }

    //Methods
    public String getImageSrc() {
        return imageSrc;
    }

    public char getSymbol() {
        return symbol;
    }

}
