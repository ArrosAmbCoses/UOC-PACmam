package edu.uoc.pacman.model.entities;

import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public abstract class Entity {
    //Attributes
    private boolean pathable;
    private Position position;
    private Sprite sprite;

    //Constructor
    protected Entity(Position position, boolean pathable, Sprite sprite) {
       setPathable(pathable);
       setPosition(position);
       setSprite(sprite);
    }

    public boolean isPathable() {
        return pathable;
    }

    public void setPathable(boolean pathable) {
        this.pathable = pathable;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    protected void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
