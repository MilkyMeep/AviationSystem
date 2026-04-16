package com.example.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Airline;
import com.example.springboot.repository.AirlineRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/airlines")
public class AirlineController {

    private final AirlineRepository airlineRepository;

    public AirlineController(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    // GET all airlines
    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    // POST create airline
    @PostMapping
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineRepository.save(airline);
    }
}
