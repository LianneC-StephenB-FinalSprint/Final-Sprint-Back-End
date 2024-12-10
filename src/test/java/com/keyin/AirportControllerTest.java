package com.keyin;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportController;
import com.keyin.airport.AirportDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


        // Create Airport objects using the existing constructor and set the city manually
        Airport airport1 = new Airport("John F. Kennedy International Airport", "JFK");


        Airport airport2 = new Airport("Los Angeles International Airport", "LAX");


        // Mock the service method to return the mock airports
        List<AirportDTO> mockAirports = List.of(
                new AirportDTO(1L, "John F. Kennedy International Airport", "JFK", "Queens, NY", "New York", "NY", null, null),
                new AirportDTO(2L, "Los Angeles International Airport", "LAX", "Los Angeles, CA", "Los Angeles", "CA", null, null)
        );
        Mockito.when(airportService.getAllAirportsAsDTOs()).thenReturn(mockAirports);
        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John F. Kennedy International Airport"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("JFK"));

    }
}