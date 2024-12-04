package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    // Find all aircraft associated with a specific airport
    List<Aircraft> findByAirports(Airport airport);

    // Find all aircraft a specific passenger has flown on
    List<Aircraft> findByPassengers(Passenger passenger);
}