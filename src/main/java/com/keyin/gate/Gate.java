package com.keyin.gate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

import com.keyin.flight.Flight;


@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Gate name, e.g., "G12"

    @Column(nullable = false)
    private String terminal; // Terminal name, e.g., "Terminal 1"

    @OneToMany(mappedBy = "gate")
    @JsonIgnore
    private List<Flight> flights; // Flights assigned to this gate

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
