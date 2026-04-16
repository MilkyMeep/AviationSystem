package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}