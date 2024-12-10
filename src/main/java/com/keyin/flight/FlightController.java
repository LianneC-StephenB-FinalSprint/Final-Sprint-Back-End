package com.keyin.flight;

import com.keyin.aircraft.AircraftRepository;
import com.keyin.airline.AirlineRepository;
import com.keyin.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin
public class FlightController {

    private final FlightService flightService;
    private final AirportRepository airportRepository;
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    @Autowired
    public FlightController(FlightService flightService, AirportRepository airportRepository,
                            AircraftRepository aircraftRepository, AirlineRepository airlineRepository) {
        this.flightService = flightService;
        this.airportRepository = airportRepository;
        this.aircraftRepository = aircraftRepository;
        this.airlineRepository = airlineRepository;
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public FlightDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.addFlight(flightDTO));
    }

    @PutMapping("/{id}")
    public FlightDTO updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}