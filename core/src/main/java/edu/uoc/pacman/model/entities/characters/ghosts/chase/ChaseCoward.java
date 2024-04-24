package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.utils.Position;

public class ChaseCoward implements ChaseBehaviour{
    //Attribute
    private static final int TILES_TO_CHASE = 8;

    //Constructor
    public ChaseCoward() {

    }

    //Methods
    @Override
    public Position getChasePosition(Ghost ghost) {
        if (ghost.getPosition().distance(ghost.getLevel().getPacman().getPosition()) >= TILES_TO_CHASE) {
            return ghost.getLevel().getPacman().getPosition();
        } else {
            return ghost.getScatterPosition();
        }
    }
}
