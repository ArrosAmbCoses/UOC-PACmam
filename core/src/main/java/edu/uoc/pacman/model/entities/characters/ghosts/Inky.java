package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Hitable;
import edu.uoc.pacman.model.entities.characters.Movable;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChasePatrol;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Inky extends Ghost implements Hitable, Movable, Scorable {
    //Attributes
    private static final int POINTS = 200;

    //Constructor
    public Inky(Position startPosition, Direction direction, Behaviour behaviour, Level level) {
        super(startPosition, new Position(level.getWidth(), level.getHeight()), direction, behaviour, Sprite.INKY, level);
        this.chaseBehaviour = new ChasePatrol();
    }

    //Methods
    @Override
    public int getPoints() {
        return POINTS;
    }

}
