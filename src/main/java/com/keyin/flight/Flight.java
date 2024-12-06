package com.keyin.flight;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.keyin.airport.Airport;
import com.keyin.aircraft.Aircraft;
import com.keyin.gate.Gate;
import com.keyin.passenger.Passenger;
import com.keyin.airline.Airline;

@Entity
@Table(name = "Flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated

    @Column(nullable = false, unique = true)
    private String flightNumber; // Unique flight identifier

    @Column(nullable = false)
    private LocalDateTime departureTime; // Departure time

    @Column(nullable = false)
    private LocalDateTime arrivalTime; // Arrival time

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport; // Origin airport relationship

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport; // Destination airport relationship

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft; // Aircraft relationship

    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate; // Gate relationship

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline; // The airline operating this flight

    //@OneToMany(mappedBy = "flight")
   //private List<Passenger> passengers; // Optional, if passengers are tracked.

    @ManyToMany(mappedBy = "flights")
    private List<Passenger> passengers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
