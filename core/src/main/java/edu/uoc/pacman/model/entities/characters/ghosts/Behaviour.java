package edu.uoc.pacman.model.entities.characters.ghosts;

import java.io.Serializable;
import java.lang.constant.Constable;

public enum Behaviour implements Serializable, Comparable<Behaviour>, Constable {
    //Enumeration
    CHASE(20),
    SCATTER(10),
    FRIGHTENED(30),
    INACTIVE(5);

    //Attributes
    private final int duration;

    //Constructor
    Behaviour(int duration) {
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
