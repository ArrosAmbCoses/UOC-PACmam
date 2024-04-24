package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Hitable;
import edu.uoc.pacman.model.entities.characters.Movable;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChaseCoward;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Clyde extends Ghost implements Hitable, Movable, Scorable {
    //Attributes
    private static final int POINTS = 100;

    //Constructor
    public Clyde(Position startPosition, Direction direction, Behaviour behaviour, Level level) {
        super(startPosition, new Position(-1, level.getHeight()), direction, behaviour, Sprite.CLYDE, level);
        this.chaseBehaviour = new ChaseCoward();
    }

    //methods
    @Override
    public int getPoints() {
        return POINTS;
    }

}
