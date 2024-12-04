package com.keyin;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportController;
import com.keyin.airport.AirportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    public void testGetListOfAirportsInDB() throws Exception {
        // Arrange: Create a list of Airport objects to be returned by the mock service
        Airport airport1 = new Airport("John F. Kennedy International Airport", "JFK");
        Airport airport2 = new Airport("Los Angeles International Airport", "LAX");
        List<Airport> airportList = Arrays.asList(airport1, airport2);

        // Mock the service to return the list of airports
        Mockito.when(airportService.getAllAirports()).thenReturn(airportList);

        // Act and Assert: Perform the GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllAirports")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John F. Kennedy International Airport"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("JFK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Los Angeles International Airport"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("LAX"));
    }
}
