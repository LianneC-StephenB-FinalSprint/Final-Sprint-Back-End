package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    // Retrieve all airports
    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Retrieve airports by city ID
    public List<Airport> getAirportsByCity(Integer cityId) {
        return airportRepository.findByCityId(cityId);
    }

    // Create a new airport
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Update an existing airport by ID
    public Airport updateAirport(Integer airportId, Airport airportDetails) {
        Optional<Airport> airportOptional = airportRepository.findById(airportId);

        if (airportOptional.isPresent()) {
            Airport existingAirport = airportOptional.get();
            existingAirport.setName(airportDetails.getName());
            existingAirport.setLocation(airportDetails.getLocation());
            existingAirport.setCode(airportDetails.getCode());
            return airportRepository.save(existingAirport);
        }
        return null; // Handle not-found case appropriately as needed
    }

    // Delete an airport by ID
    public void deleteAirport(Integer airportId) {
        airportRepository.deleteById(airportId);
    }

    public Airport findById(Integer id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport findAirportByNameCodeAndCity(String name, String code, Integer cityId) {
        // Implement the logic to find the airport
        return airportRepository.findByNameAndCodeAndCityId(name, code, cityId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }
}
