package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;

public class ChasePatrol implements ChaseBehaviour{
    //Attributes
    private static final int TILES_OFFSET = 2;
    private static final int VECTOR_INCREASE = 2;

    //Constructor
    public ChasePatrol() {

    }

    @Override
    public Position getChasePosition(Ghost ghost) {
        Direction dir = ghost.getLevel().getPacman().getDirection();
        Position targetBlinky =  new Position(ghost.getLevel().getPacman().getPosition().getX()+(dir.getX()*TILES_OFFSET),
                                              ghost.getLevel().getPacman().getPosition().getY()+(dir.getY()*TILES_OFFSET));
        if (ghost.getLevel().getBlinky() == null) {
            return targetBlinky;
        } else {
            Position objectiu = new Position(targetBlinky.getX()-ghost.getLevel().getBlinky().getPosition().getX(),
                                             targetBlinky.getY()-ghost.getLevel().getBlinky().getPosition().getY());
            return new Position(objectiu.getX()*VECTOR_INCREASE, objectiu.getY()*VECTOR_INCREASE);
        }
    }
}
