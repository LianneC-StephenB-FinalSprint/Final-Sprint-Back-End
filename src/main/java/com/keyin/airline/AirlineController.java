package com.keyin.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    @PostMapping
    public Airline addAirline(@RequestBody Airline airline) {
        return airlineService.addAirline(airline);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable Long id, @RequestBody Airline airlineDetails) {
        return airlineService.updateAirline(id, airlineDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }
}
