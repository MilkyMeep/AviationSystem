package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Gate;

public interface GateRepository extends JpaRepository<Gate, Long> {
}