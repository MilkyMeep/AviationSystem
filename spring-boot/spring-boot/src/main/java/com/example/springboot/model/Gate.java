package com.example.springboot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gateNumber;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToMany(mappedBy = "gate")
    @JsonIgnore
    private List<Flight> flights;

    // Getters & Setters
    public Long getId() {
    return id;
}

public String getGateNumber() {
    return gateNumber;
}

public void setGateNumber(String gateNumber) {
    this.gateNumber = gateNumber;
}
}