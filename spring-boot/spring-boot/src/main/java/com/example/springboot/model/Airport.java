package com.example.springboot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String location;

    @OneToMany(mappedBy = "airport")
    @JsonIgnore
    private List<Flight> flights;

    @OneToMany(mappedBy = "airport")
    @JsonIgnore
    private List<Gate> gates;

    // Getters & Setters
    





    //
    public Long getId() {
    return id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getCode() {
    return code;
}

public void setCode(String code) {
    this.code = code;
}

public String getLocation() {
    return location;
}

public void setLocation(String location) {
    this.location = location;
}
}