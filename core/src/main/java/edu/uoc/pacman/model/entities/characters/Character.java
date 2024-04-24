package edu.uoc.pacman.model.entities.characters;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Entity;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

import java.util.Objects;

public abstract class Character extends Entity implements Movable,Hitable {
    //Attributes
    private boolean dead = false;
    private Direction direction;
    private int duration;
    private Level level;
    private Position startPosition;

    protected Character(Position position, Direction direction, Sprite sprite, Level level) {
        super(position, true, sprite);
        setDirection(Objects.requireNonNullElse(direction, Direction.UP));
        setStartPosition(position);
        if (position == null) {
            Position pos = new Position(0,0);
            setPosition(pos);
            setStartPosition(pos);
        }
        setLevel(level);
    }

    //Methods
    public void reset(){
        this.alive();
        setPosition(this.startPosition);
    }

    public boolean isDead() {
        return dead;
    }

    private void setDead(boolean dead) {
        this.dead = dead;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    protected Position getStartPosition() {
        return startPosition;
    }

    private void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void kill() {
        setDead(true);
    }

    public void alive() {
        setDead(false);
    }

    public String toString() {
        String valorx = String.valueOf(this.getPosition().getX());
        String valory = String.valueOf(this.getPosition().getY());
        String valorD = String.valueOf(this.getDirection());
        return String.format("%s,%s,%s",valorx,valory,valorD);
    }
}
