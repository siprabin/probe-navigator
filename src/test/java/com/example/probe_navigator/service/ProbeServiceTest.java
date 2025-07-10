package com.example.probe_navigator.service;

import com.example.probe_navigator.dto.CommandRequest;
import com.example.probe_navigator.dto.CommandResponse;
import com.example.probe_navigator.model.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProbeServiceTest {

    ProbeService service = new ProbeService();

    @Test
    void testBasicForwardMovement() {
        CommandRequest request = new CommandRequest();
        request.setGridX(5);
        request.setGridY(5);
        request.setStartX(0);
        request.setStartY(0);
        request.setDirection("NORTH");
        request.setCommands("FF");

        CommandResponse response = service.handleRequest(request);

        assertEquals(new Position(0, 2), response.getFinalPosition());
        assertEquals("NORTH", response.getFinalDirection());
        assertEquals(3, response.getPath().size());
    }

    @Test
    void testTurningSequence() {
        CommandRequest request = new CommandRequest();
        request.setGridX(5);
        request.setGridY(5);
        request.setStartX(0);
        request.setStartY(0);
        request.setDirection("NORTH");
        request.setCommands("FRFR");

        CommandResponse response = service.handleRequest(request);

        assertEquals("SOUTH", response.getFinalDirection());
    }

    @Test
    void testObstacleAvoidance() {
        CommandRequest request = new CommandRequest();
        request.setGridX(5);
        request.setGridY(5);
        request.setStartX(0);
        request.setStartY(0);
        request.setDirection("NORTH");
        request.setCommands("F");
        request.setObstacles(Arrays.asList(new Position(0, 1)));

        CommandResponse response = service.handleRequest(request);

        assertEquals(new Position(0, 0), response.getFinalPosition());
        assertEquals(1, response.getPath().size());
    }

    @Test
    void testBoundaryConstraint() {
        CommandRequest request = new CommandRequest();
        request.setGridX(2);
        request.setGridY(2);
        request.setStartX(0);
        request.setStartY(0);
        request.setDirection("WEST");
        request.setCommands("F");

        CommandResponse response = service.handleRequest(request);

        assertEquals(new Position(0, 0), response.getFinalPosition());
    }
}