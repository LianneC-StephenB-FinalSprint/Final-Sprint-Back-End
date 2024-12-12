package com.keyin;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.airline.Airline;
import com.keyin.airline.AirlineRepository;
import com.keyin.flight.FlightController;
import com.keyin.flight.FlightDTO;
import com.keyin.flight.FlightService;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private AirportRepository airportRepository;

    @MockBean
    private AircraftRepository aircraftRepository;

    @MockBean
    private AirlineRepository airlineRepository;

    @MockBean
    private GateRepository gateRepository;

    @Test
    public void testGetAllFlights() throws Exception {
        // Prepare mock data
        FlightDTO flight1 = new FlightDTO();
        flight1.setId(1L);
        flight1.setFlightNumber("AA123");
        flight1.setDepartureTime(LocalDateTime.of(2024, 12, 20, 10, 30));
        flight1.setArrivalTime(LocalDateTime.of(2024, 12, 20, 14, 45));
        flight1.setOriginAirportName("John F. Kennedy International Airport");
        flight1.setDestinationAirportName("Los Angeles International Airport");
        flight1.setAirlineName("American Airlines");

        FlightDTO flight2 = new FlightDTO();
        flight2.setId(2L);
        flight2.setFlightNumber("DL456");
        flight2.setDepartureTime(LocalDateTime.of(2024, 12, 21, 8, 15));
        flight2.setArrivalTime(LocalDateTime.of(2024, 12, 21, 11, 50));
        flight2.setOriginAirportName("Chicago O'Hare International Airport");
        flight2.setDestinationAirportName("Hartsfield-Jackson Atlanta International Airport");
        flight2.setAirlineName("Delta Airlines");

        List<FlightDTO> mockFlights = List.of(flight1, flight2);

        // Mock the service
        Mockito.when(flightService.getAllFlights()).thenReturn(mockFlights);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/flights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flightNumber").value("AA123"))
                .andExpect(jsonPath("$[1].flightNumber").value("DL456"));
    }

    @Test
    public void testAddFlight() throws Exception {
        // Prepare mock data
        FlightDTO newFlight = new FlightDTO();
        newFlight.setFlightNumber("UA789");
        newFlight.setDepartureTime(LocalDateTime.of(2024, 12, 22, 9, 0));
        newFlight.setArrivalTime(LocalDateTime.of(2024, 12, 22, 12, 15));
        newFlight.setOriginAirportId(1L);
        newFlight.setDestinationAirportId(2L);
        newFlight.setAircraftId(1L);
        newFlight.setAirlineId(1L);

        FlightDTO savedFlight = new FlightDTO();
        savedFlight.setId(3L);
        savedFlight.setFlightNumber("UA789");
        savedFlight.setDepartureTime(newFlight.getDepartureTime());
        savedFlight.setArrivalTime(newFlight.getArrivalTime());
        savedFlight.setOriginAirportName("San Francisco International Airport");
        savedFlight.setDestinationAirportName("Denver International Airport");
        savedFlight.setAirlineName("United Airlines");

        Mockito.when(flightService.addFlight(Mockito.any(FlightDTO.class))).thenReturn(savedFlight);

        // Perform POST request and validate response
        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "flightNumber": "UA789",
                            "departureTime": "2024-12-22T09:00:00",
                            "arrivalTime": "2024-12-22T12:15:00",
                            "originAirportId": 1,
                            "destinationAirportId": 2,
                            "aircraftId": 1,
                            "airlineId": 1
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.flightNumber").value("UA789"));
    }
}