package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Hitable;
import edu.uoc.pacman.model.entities.characters.Movable;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChaseAggressive;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Blinky extends Ghost implements Hitable, Movable, Scorable {
    //Attributes
    private static final int POINTS = 400;

    //Constructor
    public Blinky(Position startPosition, Direction direction, Behaviour behaviour, Level level) {
        super(startPosition, new Position(level.getWidth(), -1), direction, behaviour, Sprite.BLINKY, level);
        this.chaseBehaviour = new ChaseAggressive();
    }

    //Methods
    @Override
    public int getPoints() {
        return POINTS;
    }

}
