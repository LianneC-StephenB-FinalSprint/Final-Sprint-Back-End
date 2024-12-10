package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.airline.Airline;
import com.keyin.airline.AirlineRepository;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;
    private final GateRepository gateRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository,
                         AircraftRepository aircraftRepository, AirlineRepository airlineRepository,
                         GateRepository gateRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.aircraftRepository = aircraftRepository;
        this.airlineRepository = airlineRepository;
        this.gateRepository = gateRepository;
    }

    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(this::convertToDTO).toList();
    }

    public FlightDTO getFlightById(Long id) {
        return flightRepository.findById(id).map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public FlightDTO addFlight(FlightDTO flightDTO) {
        Flight flight = convertToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        return convertToDTO(savedFlight);
    }

    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existingFlight.setFlightNumber(flightDTO.getFlightNumber());
        // Update other fields as necessary
        Flight updatedFlight = flightRepository.save(existingFlight);
        return convertToDTO(updatedFlight);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found");
        }
        flightRepository.deleteById(id);
    }

    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());

        if (flight.getOriginAirport() != null) {
            flightDTO.setOriginAirportName(flight.getOriginAirport().getName());
            flightDTO.setOriginAirportId(flight.getOriginAirport().getId());
        }

        if (flight.getDestinationAirport() != null) {
            flightDTO.setDestinationAirportName(flight.getDestinationAirport().getName());
            flightDTO.setDestinationAirportId(flight.getDestinationAirport().getId());
        }

        if (flight.getAirline() != null) {
            flightDTO.setAirlineName(flight.getAirline().getName());
            flightDTO.setAirlineId(flight.getAirline().getId());
        }

        if (flight.getAircraft() != null) {
            flightDTO.setAircraftId(flight.getAircraft().getId());
        }

        if (flight.getGate() != null) {
            flightDTO.setGateName(flight.getGate().getName());
        }

        return flightDTO;
    }

    private Flight convertToEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();

        // Fetch and set origin airport
        flight.setOriginAirport(
                airportRepository.findById(flightDTO.getOriginAirportId())
                        .orElseThrow(() -> new RuntimeException("Origin airport not found"))
        );

        // Fetch and set destination airport
        flight.setDestinationAirport(
                airportRepository.findById(flightDTO.getDestinationAirportId())
                        .orElseThrow(() -> new RuntimeException("Destination airport not found"))
        );

        // Set flight details
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());

        // Fetch and set airline
        flight.setAirline(
                airlineRepository.findById(flightDTO.getAirlineId())
                        .orElseThrow(() -> new RuntimeException("Airline not found"))
        );


        // Map Gate
        if (flightDTO.getGateId() != null) {
            Gate gate = gateRepository.findById(flightDTO.getGateId())
                    .orElseThrow(() -> new RuntimeException("Gate not found"));
            flight.setGate(gate);
        }


        // Fetch and set gate
        if (flightDTO.getGateName() != null) {
            Gate gate = gateRepository.findByName(flightDTO.getGateName())
                    .orElseThrow(() -> new RuntimeException("Gate not found"));
            flight.setGate(gate);
        }

        // Fetch and set aircraft
        flight.setAircraft(
                aircraftRepository.findById(flightDTO.getAircraftId())
                        .orElseThrow(() -> new RuntimeException("Aircraft not found"))
        );

        return flight;
    }
}