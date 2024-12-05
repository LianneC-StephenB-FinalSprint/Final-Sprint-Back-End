package com.keyin.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.keyin.flight.Flight;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setGate(flightDetails.getGate());
        flight.setAirline(flightDetails.getAirline());
        flight.setOriginAirport(flightDetails.getOriginAirport());
        flight.setDestinationAirport(flightDetails.getDestinationAirport());

        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flightRepository.delete(flight);
    }
}
