package com.example.probe_navigator.controller;

import com.example.probe_navigator.dto.CommandRequest;
import com.example.probe_navigator.dto.CommandResponse;
import com.example.probe_navigator.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {

    @Autowired
    private ProbeService service;

    @PostMapping("/move")
    public ResponseEntity<CommandResponse> move(@RequestBody CommandRequest req) {
        return ResponseEntity.ok(service.handleRequest(req));
    }
}
