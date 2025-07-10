package com.example.probe_navigator.model;

import lombok.Getter;

/**
 * Optional: Wrapper for obstacle configuration.
 * You can use Position directly in your DTO if preferred.
 */
@Getter
public class Obstacle {
    private int x;
    private int y;

    public Obstacle() {}
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public Position toPosition() {
        return new Position(x, y);
    }
}
