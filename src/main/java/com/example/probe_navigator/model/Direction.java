package com.example.probe_navigator.model;

/**
 * Represents the cardinal direction the probe is facing.
 * Provides methods to rotate left and right.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Returns the direction when turning left.
     * Example: NORTH → WEST
     */
    public Direction turnLeft() {
        switch (this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            default: throw new IllegalStateException("Unexpected direction: " + this);
        }
    }

    /**
     * Returns the direction when turning right.
     * Example: NORTH → EAST
     */
    public Direction turnRight() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: throw new IllegalStateException("Unexpected direction: " + this);
        }
    }

    /**
     * Calculates the new position when moving forward.
     */
    public Position moveForward(Position current) {
        switch (this) {
            case NORTH: return new Position(current.getX(), current.getY() + 1);
            case SOUTH: return new Position(current.getX(), current.getY() - 1);
            case EAST:  return new Position(current.getX() + 1, current.getY());
            case WEST:  return new Position(current.getX() - 1, current.getY());
            default: throw new IllegalStateException("Unexpected direction: " + this);
        }
    }

    /**
     * Calculates the new position when moving backward.
     */
    public Position moveBackward(Position current) {
        switch (this) {
            case NORTH, SOUTH: return new Position(current.getX(), current.getY() - 1);
            case EAST:  return new Position(current.getX() - 1, current.getY());
            case WEST:  return new Position(current.getX() + 1, current.getY());
            default: throw new IllegalStateException("Invalid direction");
        }
    }
}
