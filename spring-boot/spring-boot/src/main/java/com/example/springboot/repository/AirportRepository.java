package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}