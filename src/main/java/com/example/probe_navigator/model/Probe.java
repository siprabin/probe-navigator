package com.example.probe_navigator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the probe state, direction, and path history.
 */
public class Probe {
    private Position position;
    private Direction direction;
    private List<Position> path;

    public Probe(Position startPosition, Direction startDirection) {
        this.position = startPosition;
        this.direction = startDirection;
        this.path = new ArrayList<>();
        this.path.add(new Position(position.getX(), position.getY()));
    }

    public Position getPosition() { return position; }
    public Direction getDirection() { return direction; }
    public List<Position> getPath() { return path; }

    public void executeCommand(char command, Grid grid) {
        switch (command) {
            case 'F':
                move(grid, direction.moveForward(position));
                break;
            case 'B':
                move(grid, direction.moveBackward(position));
                break;
            case 'L':
                direction = direction.turnLeft();
                break;
            case 'R':
                direction = direction.turnRight();
                break;
            default:
                // ignore unknown commands
        }
    }

    private void move(Grid grid, Position target) {
        if (grid.isInside(target) && !grid.isBlocked(target)) {
            this.position = target;
            this.path.add(new Position(target.getX(), target.getY()));
        }
    }
}
