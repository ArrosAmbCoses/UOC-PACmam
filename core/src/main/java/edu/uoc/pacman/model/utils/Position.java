package edu.uoc.pacman.model.utils;

import java.util.Objects;

public class Position {
    //Attributes
    private int x;
    private int y;

    //Constructor
    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    //Methods
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Position add(Position p1, Position p2) throws NullPointerException {
        if (p1 == null || p2 == null) {
            throw new NullPointerException();
        } else {
            return new Position(p1.getX()+ p2.getX(), p1.getY()+ p2.getY());
        }
    }

    public double distance(Position other) {
        if (other == null) {
            return 0;
        } else {
            return Math.sqrt((other.getY() - this.getY()) * (other.getY() - this.getY()) + (other.getX() - this.getX()) * (other.getX() - this.getX()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() == this.getClass()){
            Position p2 = (Position) other;
            return this.getX() == p2.getX() && this.getY() == p2.getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(),this.getY());
    }

    @Override
    public String toString() {
        String valorx = String.valueOf(this.getX());
        String valory = String.valueOf(this.getY());
        return String.format("%s,%s",valorx,valory);
    }
}
