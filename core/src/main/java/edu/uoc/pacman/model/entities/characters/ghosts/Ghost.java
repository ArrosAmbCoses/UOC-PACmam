package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.Hitable;
import edu.uoc.pacman.model.entities.characters.Movable;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChaseBehaviour;
import edu.uoc.pacman.model.entities.characters.pacman.State;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

import java.util.ArrayList;

public abstract class Ghost extends Character implements Scorable, Hitable, Movable {
    //Attributes
    private Behaviour behaviour;
    protected ChaseBehaviour chaseBehaviour;
    private Position scatterPosition;

    //Constructor
    protected Ghost(Position startPosition,
                    Position scatterPosition,
                    Direction direction,
                    Behaviour behaviour,
                    Sprite sprite, Level level) {
        super(startPosition, direction, sprite, level);
        setBehaviour(behaviour);
        setScatterPosition(scatterPosition);
    }

    //Methods
    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        if (behaviour != null) {
            this.behaviour = behaviour;
            this.setDuration(behaviour.getDuration());
        }
    }

    private void nextBehaviour(){
        if (this.getDuration() > 0) {
            setDuration(this.getDuration()-1);
        } else if (this.getDuration() == 0) {
            if (this.getBehaviour() == Behaviour.CHASE) {
                this.setBehaviour(Behaviour.SCATTER);
            } else {
                this.setBehaviour(Behaviour.CHASE);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        setBehaviour(Behaviour.INACTIVE);
        setDirection(Direction.UP);
    }

    @Override
    public boolean equals(Object o) {
        Ghost fantasma = (Ghost) o;
        boolean A = this.isDead() == fantasma.isDead();
        boolean B = this.getBehaviour() == fantasma.getBehaviour();
        boolean C = this.getDirection() == fantasma.getDirection();
        boolean D = this.getPosition().equals(fantasma.getPosition());
        boolean E = this.getDuration() == fantasma.getDuration();
        return A && B && C && D && E;
    }

    @Override
    public String toString() {
        String valor1 = String.valueOf(this.getPosition().getX());
        String valor2 = String.valueOf(this.getPosition().getY());
        String valor3 = String.valueOf(this.getDirection());
        String valor4 = String.valueOf(this.getBehaviour());
        return String.format("%s,%s,%s,%s",valor1,valor2,valor3,valor4);
    }

    public Position getScatterPosition() {
        return scatterPosition;
    }

    private void setScatterPosition(Position scatterPosition) {
        this.scatterPosition = scatterPosition;
    }

    private Position getTargetPosition() {
        if (this.getBehaviour() == Behaviour.CHASE) {
            return this.chaseBehaviour.getChasePosition(this);
        } else if (this.getBehaviour() == Behaviour.SCATTER || this.getBehaviour() == Behaviour.FRIGHTENED) {
            return this.getScatterPosition();
        } else {
            return null;
        }
    }

    public void move() {
        if (this.getTargetPosition() != null) {
            //agafe les direccions possibles
            ArrayList<Direction> directions = new ArrayList<>();
            directions.add(Direction.RIGHT);
            directions.add(Direction.DOWN);
            directions.add(Direction.LEFT);
            directions.add(Direction.UP);
            directions.removeIf(i -> this.getDirection().opposite() == i);

            //calcule les posicions possibles
            ArrayList<Position> potentials = new ArrayList<>();
            for (Direction i : directions) {
                Position pos = Position.add(this.getPosition(), new Position(i.getX(),i.getY()));
                //mire si és pathable
                if (this.getLevel().getMapItem(pos).isPathable()) {
                    potentials.add(pos);
                } else {
                    potentials.add(null);
                }
            }

            //guarde les distancies
            ArrayList<Double> dists = new ArrayList<>();
            for (Position i : potentials) {
                if (i != null) {
                    Position di = this.getTargetPosition();
                    dists.add(i.distance(di));
                } else {
                    dists.add(Double.MAX_VALUE);
                }
            }

            //minim
            double mindist = dists.get(0);
            Position minpos = potentials.get(0);
            Direction mindir = directions.get(0);
            ArrayList<Position> equalpos = new ArrayList<>();
            ArrayList<Direction> equaldir = new ArrayList<>();
            for (int i = 1; i < dists.size(); i++) {
                //si es menor
                if (mindist > dists.get(i)) {
                    //guarde la dist i la pos
                    mindist = dists.get(i);
                    minpos = potentials.get(i);
                    mindir = directions.get(i);
                    //si son iguals
                } else if (mindist == dists.get(i)) {
                    //si la llista de iguals està buida
                    if (equalpos.isEmpty()) {
                        //guarde les pos i dir del menor anterior i actual
                        equalpos.add(minpos);
                        equalpos.add(potentials.get(i));
                        equaldir.add(mindir);
                        equaldir.add(directions.get(i));
                    } else {
                        //guarde la pos del actual
                        equalpos.add(potentials.get(i));
                        equaldir.add(directions.get(i));
                    }
                }
            }
            //si la llista està plena agafarem l'última posició en l'ordre de la direcció
            if (!equalpos.isEmpty()) {
                this.setPosition(equalpos.get(equalpos.size() - 1));
                this.setDirection(equaldir.get(equaldir.size() - 1));
                this.hit();
            } else {
                this.setPosition(minpos);
                this.setDirection(mindir);
                this.hit();
            }
        }
        //passa sempre
        this.nextBehaviour();
    }

    public boolean hit() {
        Behaviour valor = this.getBehaviour();
        Position pos = this.getPosition();
        if (this.getLevel().getPacman() != null &&
                pos.equals(this.getLevel().getPacman().getPosition()) &&
                valor != Behaviour.INACTIVE){
            if (valor == Behaviour.FRIGHTENED) {
                this.kill();
                return true;
            } else if (this.getLevel().getPacman().getState() == State.NORMAL) {
                this.getLevel().getPacman().kill();
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void kill() {
        super.kill();
        this.setBehaviour(Behaviour.INACTIVE);
        this.getLevel().addPoints(this.getPoints());
    }


}
