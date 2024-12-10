package com.keyin.airport;

import com.keyin.flight.Flight;

import java.util.List;

public class AirportDTO {
    private Long id;
    private String name;
    private String code;
    private String location;
    private String cityName;
    private String cityState;

    private List<Flight> departingFlights;
    private List<Flight> arrivingFlights;

    // Constructor
    public AirportDTO(Long id, String name, String code, String location, String cityName, String cityState,
                      List<Flight> departingFlights, List<Flight> arrivingFlights) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.location = location;
        this.cityName = cityName;
        this.cityState = cityState;
        this.departingFlights = departingFlights;
        this.arrivingFlights = arrivingFlights;
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

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getCityState() { return cityState; }
    public void setCityState(String cityState) { this.cityState = cityState; }

    public List<Flight> getDepartingFlights() { return departingFlights; }
    public void setDepartingFlights(List<Flight> departingFlights) { this.departingFlights = departingFlights; }

    public List<Flight> getArrivingFlights() { return arrivingFlights; }
    public void setArrivingFlights(List<Flight> arrivingFlights) { this.arrivingFlights = arrivingFlights; }
}