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

    // GET all airports
    @GetMapping
    public Iterable<Airport> getListOfAirportsInDB() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Integer id) {
        Airport airport = airportService.findById(id);
        if (airport != null) {
            return ResponseEntity.ok(airport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        Integer cityId = airport.getCity().getId(); // Assuming city is part of the Airport object
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isPresent()) {
            airport.setCity(cityOptional.get());
            return airportService.createAirport(airport);
        } else {
            throw new RuntimeException("City not found with ID: " + cityId);
        }
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Integer id, @RequestBody Airport airportDetails, @RequestParam Integer cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isPresent()) {
            airportDetails.setCity(cityOptional.get());
            return airportService.updateAirport(id, airportDetails);
        } else {
            throw new RuntimeException("City not found with ID: " + cityId);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Integer id) {
        airportService.deleteAirport(id);
    }

    @GetMapping("/byCity/{cityId}")
    public List<Airport> getAirportsByCity(@PathVariable Integer cityId) {
        return airportService.getAirportsByCity(cityId);
    }

    // New endpoint to get airport information by name, code, and cityId
    @GetMapping("/info")
    public Airport getAirportInfo(@RequestParam String name, @RequestParam String code, @RequestParam Integer cityId) {
        // Logic to fetch the airport by name, code, and cityId
        return airportService.findAirportByNameCodeAndCity(name, code, cityId);
    }
}