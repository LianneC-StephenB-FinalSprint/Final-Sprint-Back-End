package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Airport> getAirportsByCity(Long cityId) {
        return airportRepository.findByCityId(cityId);
    }

    // Create a new airport
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Update an existing airport by ID
    public Airport updateAirport(Long airportId, Airport airportDetails) {
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
    public void deleteAirport(Long airportId) {
        airportRepository.deleteById(airportId);
    }

    public Airport findById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport findAirportByNameCodeAndCity(String name, String code, Long cityId) {
        // Implement the logic to find the airport
        return airportRepository.findByNameAndCodeAndCityId(name, code, cityId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public AirportDTO mapToDTO(Airport airport) {
        return new AirportDTO(
                airport.getId(),
                airport.getName(),
                airport.getCode(),
                airport.getLocation(),
                airport.getCity().getName(),
                airport.getCity().getState(),
                airport.getDepartingFlights(),
                airport.getArrivingFlights()
        );
    }

    public List<AirportDTO> getAllAirportsAsDTOs() {
        return airportRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AirportDTO getAirportDTOById(Long id) {
        Airport airport = findById(id);
        if (airport != null) {
            return mapToDTO(airport);
        }
        throw new RuntimeException("Airport not found with ID: " + id);
    }
}