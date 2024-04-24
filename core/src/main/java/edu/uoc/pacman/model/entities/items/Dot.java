package edu.uoc.pacman.model.entities.items;

import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Dot extends MapItem implements Pickable, Scorable {
    //Attributes
    private boolean picked = false;
    private static final int POINTS = 1;

    //Constructor
    public Dot(Position position) {
        super(position, true, Sprite.DOT);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

    @Override
    public boolean isPicked() {
        return this.picked;
    }

    @Override
    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}
