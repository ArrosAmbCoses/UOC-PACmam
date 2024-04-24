package edu.uoc.pacman.model.entities.characters.pacman;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.Hitable;
import edu.uoc.pacman.model.entities.characters.Movable;
import edu.uoc.pacman.model.entities.characters.ghosts.Behaviour;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.items.*;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

import java.util.List;

public class Pacman extends Character implements Hitable, Movable {
    //Attributes
    private State state;

    //Constructor
    public Pacman(Position startPosition, Direction direction, State state, Level level) {
        super(startPosition, direction, Sprite.PACMAN_UP, level);
        setState(state);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (state != null) {
            this.state = state;
            this.setDuration(state.getDuration());
        }
    }

    private void nextState() {
        if (this.getDuration() > 0) {
            setDuration(this.getDuration()-1);
        } else if (this.getDuration() == 0) {
            this.setState(State.NORMAL);
        }
    }

    @Override
    public void reset() {
        super.reset();
        setState(State.INVINCIBLE);
        setDirection(Direction.UP);
    }

    @Override
    public void move() {
        Position pos = Position.add(this.getPosition(),new Position(this.getDirection().getX(),this.getDirection().getY()));
        if (this.getLevel().getMapItem(pos).isPathable()) {
            this.setPosition(pos);
            this.eat();
            this.hit();
        }
        this.nextState();
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
        if (direction == Direction.UP) {
            this.setSprite(Sprite.PACMAN_UP);
        } else if (direction == Direction.LEFT) {
            this.setSprite(Sprite.PACMAN_LEFT);
        } else if (direction == Direction.RIGHT) {
            this.setSprite(Sprite.PACMAN_RIGHT);
        } else if (direction == Direction.DOWN) {
            this.setSprite(Sprite.PACMAN_DOWN);
        }
    }

    private void eat() {
        if (this.getLevel().getMapItem(this.getPosition()).getClass() == Dot.class) {
            Dot punt = (Dot) this.getLevel().getMapItem(this.getPosition());
            punt.setPicked(true);
            this.getLevel().addPoints(punt.getPoints());

            this.getLevel().removeMapItem(this.getLevel().getMapItem(this.getPosition()));
            this.getLevel().addMapItem(new Path(this.getPosition()));
        } else if (this.getLevel().getMapItem(this.getPosition()).getClass() == Energizer.class) {
            Energizer energ = (Energizer) this.getLevel().getMapItem(this.getPosition());
            energ.setPicked(true);
            this.getLevel().addPoints(energ.getPoints());
            this.getLevel().setGhostsFrightened();
            this.setState(State.EATER);

            this.getLevel().removeMapItem(this.getLevel().getMapItem(this.getPosition()));
            this.getLevel().addMapItem(new Path(this.getPosition()));
        } else if (this.getLevel().getMapItem(this.getPosition()).getClass() == Life.class) {
            Life vida = (Life) this.getLevel().getMapItem(this.getPosition());
            vida.setPicked(true);
            this.getLevel().increaseLives();

            this.getLevel().removeMapItem(this.getLevel().getMapItem(this.getPosition()));
            this.getLevel().addMapItem(new Path(this.getPosition()));
        }
    }


    @Override
    public boolean hit() {
        if (this.getState() != State.INVINCIBLE) {
            List<Ghost> fantasmes = this.getLevel().getGhostList();
            for (Ghost g : fantasmes) {
                if (this.getPosition().equals(g.getPosition())) {
                    if (g.getBehaviour() == Behaviour.FRIGHTENED) {
                        g.kill();
                        return true;
                    } else if (g.getBehaviour() == Behaviour.INACTIVE) {
                        return false;
                    } else if (g.getBehaviour() == Behaviour.CHASE || g.getBehaviour() == Behaviour.SCATTER){
                        this.kill();
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void kill() {
        super.kill();
        this.getLevel().decreaseLives();
        this.setState(State.INVINCIBLE);
    }

}
