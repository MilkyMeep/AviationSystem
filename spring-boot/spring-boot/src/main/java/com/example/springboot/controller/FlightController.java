package com.example.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.springboot.model.Airline;
import com.example.springboot.model.Airport;
import com.example.springboot.model.Flight;
import com.example.springboot.model.Gate;
import com.example.springboot.repository.AirlineRepository;
import com.example.springboot.repository.AirportRepository;
import com.example.springboot.repository.FlightRepository;
import com.example.springboot.repository.GateRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final GateRepository gateRepository;

    public FlightController(
        FlightRepository flightRepository,
        AirportRepository airportRepository,
        AirlineRepository airlineRepository,
        GateRepository gateRepository
    ) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.gateRepository = gateRepository;
    }

    // GET all flights
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // GET flight by ID
    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // POST create flight
    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {

        // Attach Airport
        if (flight.getAirport() != null && flight.getAirport().getId() != null) {
            Airport airport = airportRepository.findById(flight.getAirport().getId()).orElse(null);
            flight.setAirport(airport);
        }

        // Attach Airline
        if (flight.getAirline() != null && flight.getAirline().getId() != null) {
            Airline airline = airlineRepository.findById(flight.getAirline().getId()).orElse(null);
            flight.setAirline(airline);
        }

        // ✅ Attach Gate (THIS WAS MISSING)
        if (flight.getGate() != null && flight.getGate().getId() != null) {
            Gate gate = gateRepository.findById(flight.getGate().getId()).orElse(null);
            flight.setGate(gate);
        }

        return flightRepository.save(flight);
    }

    // PUT update flight
    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {

        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight != null) {
            flight.setFlightNumber(updatedFlight.getFlightNumber());
            flight.setStatus(updatedFlight.getStatus());
            flight.setDepartureTime(updatedFlight.getDepartureTime());
            flight.setArrivalTime(updatedFlight.getArrivalTime());

            // Update Airport
            if (updatedFlight.getAirport() != null && updatedFlight.getAirport().getId() != null) {
                Airport airport = airportRepository.findById(updatedFlight.getAirport().getId()).orElse(null);
                flight.setAirport(airport);
            }

            // Update Airline
            if (updatedFlight.getAirline() != null && updatedFlight.getAirline().getId() != null) {
                Airline airline = airlineRepository.findById(updatedFlight.getAirline().getId()).orElse(null);
                flight.setAirline(airline);
            }

            // ✅ Update Gate
            if (updatedFlight.getGate() != null && updatedFlight.getGate().getId() != null) {
                Gate gate = gateRepository.findById(updatedFlight.getGate().getId()).orElse(null);
                flight.setGate(gate);
            }

            return flightRepository.save(flight);
        }

        return null;
    }

    // DELETE flight
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightRepository.deleteById(id);
    }
}