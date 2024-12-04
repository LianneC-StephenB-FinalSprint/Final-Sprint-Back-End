package com.keyin.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Integer id) { // Change to Long
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.createPassenger(passenger);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable Integer id, @RequestBody Passenger passengerDetails) {
        return passengerService.updatePassenger(id, passengerDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Integer id) { // Change to Long
        passengerService.deletePassenger(id);
    }
}