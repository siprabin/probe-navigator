package com.example.probe_navigator.model;
import org.junit.jupiter.api.Test;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

    @Test
    void shouldMoveForward_whenFacingNorthAndNoObstacle() {
        // Given a probe at (2,2) facing NORTH
        Grid grid = new Grid(5, 5, Set.of());
        Probe probe = new Probe(new Position(2, 2), Direction.NORTH);

        // When it moves forward
        probe.executeCommand('F', grid);

        // Then it should end up at (2,3)
        assertEquals(new Position(2, 3), probe.getPosition());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void shouldMoveBackward_whenFacingSouthAndNoObstacle() {
        // Given a probe at (2,2) facing SOUTH
        Grid grid = new Grid(5, 5, Set.of());
        Probe probe = new Probe(new Position(2, 2), Direction.SOUTH);

        // When it moves backward
        probe.executeCommand('B', grid);

        // Then it should move to (2,1)
        assertEquals(new Position(2, 1), probe.getPosition());
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void shouldNotMoveIntoObstacle_whenObstacleIsAhead() {
        // Given a probe at (1,1) facing EAST and an obstacle at (2,1)
        Grid grid = new Grid(5, 5, Set.of(new Position(2, 1)));
        Probe probe = new Probe(new Position(1, 1), Direction.EAST);

        // When it tries to move forward
        probe.executeCommand('F', grid);

        // Then it should remain at (1,1)
        assertEquals(new Position(1, 1), probe.getPosition());
    }

    @Test
    void shouldNotCrossGridBoundary_whenMovingOutsideGrid() {
        // Given a probe at edge (0,0) facing WEST
        Grid grid = new Grid(3, 3, Set.of());
        Probe probe = new Probe(new Position(0, 0), Direction.WEST);

        // When it moves forward (would go to -1,0)
        probe.executeCommand('F', grid);

        // Then it should stay in place
        assertEquals(new Position(0, 0), probe.getPosition());
    }

    @Test
    void shouldTurnLeftFromNorthToWest() {
        // Given a probe facing NORTH
        Grid grid = new Grid(5, 5, Set.of());
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);

        // When it turns left
        probe.executeCommand('L', grid);

        // Then it should face WEST
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void shouldTurnRightFromWestToNorth() {
        // Given a probe facing WEST
        Grid grid = new Grid(5, 5, Set.of());
        Probe probe = new Probe(new Position(0, 0), Direction.WEST);

        // When it turns right
        probe.executeCommand('R', grid);

        // Then it should face NORTH
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void shouldFollowSequenceCorrectly_andTrackPath() {
        // Given a probe starting at (0,0) facing NORTH
        Grid grid = new Grid(5, 5, Set.of());
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);

        // When it executes a sequence: F, R, F
        probe.executeCommand('F', grid); // move to (0,1)
        probe.executeCommand('R', grid); // turn to EAST
        probe.executeCommand('F', grid); // move to (1,1)

        // Then it should be at (1,1), facing EAST
        assertEquals(new Position(1, 1), probe.getPosition());
        assertEquals(Direction.EAST, probe.getDirection());

        // And the path should include all visited positions
        assertEquals(
                Set.of(new Position(0, 0), new Position(0, 1), new Position(1, 1)),
                Set.copyOf(probe.getPath())
        );
    }
}