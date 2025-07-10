package com.example.probe_navigator.dto;

import com.example.probe_navigator.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandResponse {
    private Position finalPosition;
    private String finalDirection;
    private List<Position> path;
}
