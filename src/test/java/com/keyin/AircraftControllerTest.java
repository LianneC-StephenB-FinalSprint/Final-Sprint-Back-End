package com.keyin;

import com.keyin.aircraft.AircraftController;
import com.keyin.aircraft.AircraftService;
import com.keyin.aircraft.Aircraft;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Test
    public void testGetAllAircraft() throws Exception {
        when(aircraftService.getAllAircraft()).thenReturn(List.of(
                new Aircraft("Passenger", "Boeing", 200),
                new Aircraft("Passenger", "Airbus", 180)
        ));

        mockMvc.perform(get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].type").value("Passenger"))
                .andExpect(jsonPath("$[0].airlineName").value("Boeing"))
                .andExpect(jsonPath("$[0].numberOfPassengers").value(200))
                .andExpect(jsonPath("$[1].type").value("Passenger"))
                .andExpect(jsonPath("$[1].airlineName").value("Airbus"))
                .andExpect(jsonPath("$[1].numberOfPassengers").value(180));

        verify(aircraftService).getAllAircraft();
    }

    @Test
    public void testCreateAircraft() throws Exception {
        Aircraft aircraft = new Aircraft("Cargo", "FedEx", 50);
        when(aircraftService.createAircraft(any(Aircraft.class))).thenReturn(aircraft);

        String aircraftJson = "{\"type\":\"Cargo\",\"airlineName\":\"FedEx\",\"numberOfPassengers\":50}";

        mockMvc.perform(post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aircraftJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Cargo"))
                .andExpect(jsonPath("$.airlineName").value("FedEx"))
                .andExpect(jsonPath("$.numberOfPassengers").value(50));

        verify(aircraftService).createAircraft(any(Aircraft.class));
    }

    @Test
    public void testGetAircraftById() throws Exception {
        Aircraft aircraft = new Aircraft("Passenger", "Delta", 220);
        when(aircraftService.getAircraftById(1)).thenReturn(aircraft);

        mockMvc.perform(get("/api/aircraft/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Passenger"))
                .andExpect(jsonPath("$.airlineName").value("Delta"))
                .andExpect(jsonPath("$.numberOfPassengers").value(220));

        verify(aircraftService).getAircraftById(1);
    }

    @Test
    public void testUpdateAircraft() throws Exception {
        Aircraft updatedAircraft = new Aircraft("Passenger", "United Airlines", 300);
        when(aircraftService.updateAircraft(eq(1), any(Aircraft.class))).thenReturn(updatedAircraft);

        String updatedAircraftJson = "{\"type\":\"Passenger\",\"airlineName\":\"United Airlines\",\"numberOfPassengers\":300}";

        mockMvc.perform(put("/api/aircraft/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAircraftJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Passenger"))
                .andExpect(jsonPath("$.airlineName").value("United Airlines"))
                .andExpect(jsonPath("$.numberOfPassengers").value(300));

        verify(aircraftService).updateAircraft(eq(1), any(Aircraft.class));
    }

    @Test
    public void testDeleteAircraft() throws Exception {
        mockMvc.perform(delete("/api/aircraft/1"))
                .andExpect(status().isOk());

        verify(aircraftService).deleteAircraft(1);
    }
}
