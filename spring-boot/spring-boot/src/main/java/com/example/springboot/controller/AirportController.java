package com.example.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Airport;
import com.example.springboot.repository.AirportRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // GET all airports
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // POST create airport
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }
}