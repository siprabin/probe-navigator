package com.example.probe_navigator.model;

import lombok.Getter;

import java.util.Set;

/**
 * Represents the grid with size and obstacle map.
 */
public class Grid {
    private final int maxX;
    private final int maxY;
    @Getter
    private final Set<Position> obstacles;

    public Grid(int maxX, int maxY, Set<Position> obstacles) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.obstacles = obstacles;
    }

    public boolean isInside(Position p) {
        return p.getX() >= 0 && p.getX() < maxX &&
                p.getY() >= 0 && p.getY() < maxY;
    }

    public boolean isBlocked(Position p) {
        return obstacles.contains(p);
    }

}
