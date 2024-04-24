package edu.uoc.pacman.model.entities.characters.pacman;

import java.io.Serializable;
import java.lang.constant.Constable;

public enum State implements Serializable, Comparable<State>, Constable {
    //Enumeration
    NORMAL(Integer.MAX_VALUE),
    EATER(30),
    INVINCIBLE(5);

    //Attributes
    private final int duration;

    //Constructor
    State(int duration) {
        this.duration = duration;
    }

    //Methods
    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        String valor1 = this.name();
        String valor2 = String.valueOf(this.getDuration());
        return String.format("%s:%s",valor1,valor2);
    }
}
