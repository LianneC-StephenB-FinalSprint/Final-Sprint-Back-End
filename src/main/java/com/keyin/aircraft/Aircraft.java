package com.keyin.aircraft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String airlineName;
    private Integer numberOfPassengers;

    public Aircraft() {
    }

    // Relationship with Airport (Many-to-Many)
    @ManyToMany
    @JoinTable(
            name = "aircraft_airport",
            joinColumns = @JoinColumn(name = "aircraft_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    //@JsonBackReference
    //@JsonIgnore
    private List<Airport> airports = new ArrayList<>();

    // Relationship with Passenger (Many-to-Many)
    @ManyToMany(mappedBy = "aircraftList")
    //@JsonManagedReference
    @JsonIgnore
    private List<Passenger> passengers = new ArrayList<>();


    public Aircraft(String type, String airlineName, Integer numberOfPassengers) {
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports; }

    public List<Passenger> getPassengers() {
        return passengers; }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers; }
}