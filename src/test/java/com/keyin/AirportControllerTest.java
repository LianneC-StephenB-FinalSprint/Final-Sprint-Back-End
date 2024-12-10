package com.keyin;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportController;
import com.keyin.airport.AirportService;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @MockBean
    private CityRepository cityRepository; // Mock the missing CityRepository

    @Test
    public void testGetListOfAirportsInDB() throws Exception {
        // Create City objects using the appropriate constructor
        City city1 = new City("New York", "NY", 8000000);
        city1.setId(1); // Set the ID if it's required

        City city2 = new City("Los Angeles", "CA", 4000000);
        city2.setId(2); // Set the ID if it's required

        // Create Airport objects using the existing constructor and set the city manually
        Airport airport1 = new Airport("John F. Kennedy International Airport", "JFK");
        airport1.setCity(city1); // Set the city manually

        Airport airport2 = new Airport("Los Angeles International Airport", "LAX");
        airport2.setCity(city2); // Set the city manually

        // Mock the service method to return the mock airports
        List<Airport> mockAirports = List.of(airport1, airport2);
        Mockito.when(airportService.getAllAirports()).thenReturn(mockAirports);

        // Perform the request and assert the results
        mockMvc.perform(MockMvcRequestBuilders.get("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John F. Kennedy International Airport"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("JFK"));
    }
}