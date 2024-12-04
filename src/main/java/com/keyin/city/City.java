    package com.keyin.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyin.airport.Airport;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String state;
    private Integer population;

    // Relationship with Airport


    @JsonIgnore // This field will be ignored during serialization
    @OneToMany(mappedBy = "city")
    private List<Airport> airports;

    //@OneToMany(mappedBy = "city")
    //@JsonManagedReference  // Marks this side as the parent side
    //private List<Airport> airports;

    public City() {}

    public City(String name, String state, Integer population) {
        this.name = name;
        this.state = state;
        this.population = population;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }

    public List<Airport> getAirports() { return airports; }
    public void setAirports(List<Airport> airports) { this.airports = airports; }

        @Override
    public String toString() {
        return "City{id=" + id + ", name='" + name + "', state='" + state + "', population=" + population + "}";
    }
}