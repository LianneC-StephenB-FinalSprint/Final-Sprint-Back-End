package com.keyin.passenger;


import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + id));

        passenger.setFirstName(passengerDetails.getFirstName());
        passenger.setLastName(passengerDetails.getLastName());
        passenger.setPhoneNumber(passengerDetails.getPhoneNumber());

        // Update the airport if necessary
        if (passengerDetails.getAirport() != null) {
            // Assuming you have an AirportRepository to find the airport by ID
            Airport airport = airportRepository.findById(passengerDetails.getAirport().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id " + passengerDetails.getAirport().getId()));
            passenger.setAirport(airport);
        }

        passenger.setAircraftList(passengerDetails.getAircraftList());

        return passengerRepository.save(passenger);
    }
    public void deletePassenger(Long id) { // Change to Long
        passengerRepository.deleteById(id);
    }
}