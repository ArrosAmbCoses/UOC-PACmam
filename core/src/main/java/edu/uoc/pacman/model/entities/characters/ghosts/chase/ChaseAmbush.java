package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;

public class ChaseAmbush implements ChaseBehaviour{
    //Attribute
    private static final int TILES_OFFSET = 4;

    //Constructor
    public ChaseAmbush() {

    }

    //Method
    @Override
    public Position getChasePosition(Ghost ghost) {
        Direction dir = ghost.getLevel().getPacman().getDirection();
        return new Position(ghost.getLevel().getPacman().getPosition().getX()+(dir.getX()*TILES_OFFSET),
                            ghost.getLevel().getPacman().getPosition().getY()+(dir.getY()*TILES_OFFSET));
    }
}
