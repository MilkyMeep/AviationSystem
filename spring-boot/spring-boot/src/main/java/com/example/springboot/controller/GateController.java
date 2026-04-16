package com.example.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Gate;
import com.example.springboot.repository.GateRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gates")
public class GateController {

    private final GateRepository gateRepository;

    public GateController(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    // GET all gates
    @GetMapping
    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    // POST create gate
    @PostMapping
    public Gate createGate(@RequestBody Gate gate) {
        return gateRepository.save(gate);
    }
}