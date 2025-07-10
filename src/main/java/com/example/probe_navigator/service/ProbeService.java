package com.example.probe_navigator.service;


import com.example.probe_navigator.dto.CommandRequest;
import com.example.probe_navigator.dto.CommandResponse;
import org.springframework.stereotype.Service;

import com.example.probe_navigator.model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles probe navigation commands and orchestrates grid interactions.
 */
@Service
public class ProbeService {

    /**
     * Processes a command request to control the probe's movement.
     *
     * @param request the incoming navigation command
     * @return the final status of the probe including path history
     */
    public CommandResponse handleRequest(CommandRequest request) {
        // Parse grid dimensions and obstacle list
        Set<Position> obstacleSet = new HashSet<>();
        if (request.getObstacles() != null) {
            obstacleSet.addAll(request.getObstacles());
        }

        Grid grid = new Grid(request.getGridX(), request.getGridY(), obstacleSet);

        // Initialize probe with starting position and direction
        Position start = new Position(request.getStartX(), request.getStartY());
        Direction facing = Direction.valueOf(request.getDirection().toUpperCase());
        Probe probe = new Probe(start, facing);

        // Execute movement commands
        char[] instructions = request.getCommands().toCharArray();
        for (char command : instructions) {
            probe.executeCommand(command, grid);
        }

        // Package final response
        return new CommandResponse(
                probe.getPosition(),
                probe.getDirection().name(),
                probe.getPath()
        );
    }
}
