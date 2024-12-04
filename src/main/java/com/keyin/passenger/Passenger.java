package com.keyin.passenger;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.city.City;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(
            name = "passenger_aircraft",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id")
    )
    @JsonBackReference
    private List<Aircraft> aircraftList = new ArrayList<>();

    // New field to store the associated airport
    @ManyToOne
    @JoinColumn(name = "airport_id") // Adjust the column name as necessary
    private Airport airport;

    public Passenger() {}

    public Passenger(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public List<Aircraft> getAircraftList() { return aircraftList; }
    public void setAircraftList(List<Aircraft> aircraftList) { this.aircraftList = aircraftList; }

    public Integer getAirportId() {
        return airport != null ? airport.getId() : null; // Returns null if airport is not set
    }

    public void setAirportId(Integer airportId) {
        if (airportId != null) {
            this.airport = new Airport();
            this.airport.setId(airportId);
        } else {
            this.airport = null; // Set to null if no ID is provided
        }
    }

    // New getter for airport
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    // Method to return the full name
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Passenger{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "', phoneNumber='" + phoneNumber + "'}";
    }
}