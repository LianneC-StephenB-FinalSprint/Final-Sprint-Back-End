package com.keyin.airline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Airline name, e.g., "Delta Airlines"

    @Column(nullable = false, unique = true)
    private String code; // Airline code, e.g., "DL"

    @OneToMany(mappedBy = "airline")
    @JsonIgnore
    private List<com.keyin.flight.Flight> flights; // Flights operated by this airline

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<com.keyin.flight.Flight> getFlights() { return flights; }
    public void setFlights(List<com.keyin.flight.Flight> flights) { this.flights = flights; }
}
