package com.keyin.airport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyin.aircraft.Aircraft;
import com.keyin.flight.Flight;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private com.keyin.city.City city;

    @ManyToMany(mappedBy = "airports")
    //@JsonManagedReference  // This ensures the forward reference in `Airport` will be serialized
    @JsonIgnore
    private List<Aircraft> aircraft = new ArrayList<>();

    @OneToMany(mappedBy = "originAirport")
    //@JsonIgnore
   @JsonManagedReference
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "destinationAirport")
    //@JsonIgnore
    @JsonManagedReference
    private List<Flight> arrivingFlights;

    // Default Constructor
    public Airport() {
    }

    // Parameterized Constructor
    public Airport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Airport(String originAirportName) {
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public com.keyin.city.City getCity() { return city; }
    public void setCity(com.keyin.city.City city) { this.city = city; }

    public List<com.keyin.aircraft.Aircraft> getAircraft() { return aircraft; }
    public void setAircraft(List<com.keyin.aircraft.Aircraft> aircraft) { this.aircraft = aircraft; }

    public List<Flight> getDepartingFlights() { return departingFlights; }
    public void setDepartingFlights(List<Flight> departingFlights) { this.departingFlights = departingFlights; }

    public List<Flight> getArrivingFlights() { return arrivingFlights; }
    public void setArrivingFlights(List<Flight> arrivingFlights) { this.arrivingFlights = arrivingFlights; }
}