package com.example.probe_navigator.dto;

import com.example.probe_navigator.model.Position;
import lombok.Data;

import java.util.List;

@Data
public class CommandRequest {
    private int gridX;
    private int gridY;
    private int startX;
    private int startY;
    private String direction; // NORTH, EAST, SOUTH, WEST
    private String commands; // e.g., "FFLBBR - F-FRONT, B-BACK, L-LEFT, R-RIGHT"
    private List<Position> obstacles; //X,Y POSITION
}
