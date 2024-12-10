package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private CityRepository cityRepository;

    // GET all airports and return them as AirportDTOs
    @GetMapping
    public List<AirportDTO> getListOfAirportsInDB() {
        return airportService.getAllAirportsAsDTOs();
    }

    // GET airport by ID and return it as AirportDTO
    @GetMapping("/{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(airportService.getAirportDTOById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST to create a new airport, associating it with a city
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        Long cityId = airport.getCity().getId(); // Assuming city is part of the Airport object
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isPresent()) {
            airport.setCity(cityOptional.get());
            return airportService.createAirport(airport);
        } else {
            throw new RuntimeException("City not found with ID: " + cityId);
        }
    }

    // PUT to update an existing airport by ID
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails, @RequestParam Long cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isPresent()) {
            airportDetails.setCity(cityOptional.get());
            return airportService.updateAirport(id, airportDetails);
        } else {
            throw new RuntimeException("City not found with ID: " + cityId);
        }
    }

    // DELETE an airport by ID
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    // GET airports by city ID
    @GetMapping("/byCity/{cityId}")
    public List<Airport> getAirportsByCity(@PathVariable Long cityId) {
        return airportService.getAirportsByCity(cityId);
    }

    // New endpoint to get airport information by name, code, and cityId
    @GetMapping("/info")
    public Airport getAirportInfo(@RequestParam String name, @RequestParam String code, @RequestParam Long cityId) {
        return airportService.findAirportByNameCodeAndCity(name, code, cityId);
    }
}