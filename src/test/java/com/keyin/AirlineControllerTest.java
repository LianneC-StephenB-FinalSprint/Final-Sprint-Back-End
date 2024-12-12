package com.keyin.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirlineController.class)
public class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddAirline() throws Exception {
        Airline requestAirline = new Airline();
        requestAirline.setName("Delta Airlines");
        requestAirline.setCode("DL");

        Airline responseAirline = new Airline();
        responseAirline.setId(1L);
        responseAirline.setName("Delta Airlines");
        responseAirline.setCode("DL");

        Mockito.when(airlineService.addAirline(Mockito.any(Airline.class))).thenReturn(responseAirline);

        mockMvc.perform(post("/api/airlines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestAirline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Delta Airlines"))
                .andExpect(jsonPath("$.code").value("DL"));
    }

    @Test
    public void testGetAirlineById() throws Exception {
        Airline mockAirline = new Airline();
        mockAirline.setId(1L);
        mockAirline.setName("Delta Airlines");
        mockAirline.setCode("DL");

        Mockito.when(airlineService.getAirlineById(1L)).thenReturn(mockAirline);

        mockMvc.perform(get("/api/airlines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Delta Airlines"))
                .andExpect(jsonPath("$.code").value("DL"));
    }

    @Test
    public void testUpdateAirline() throws Exception {
        Airline updatedAirlineDetails = new Airline();
        updatedAirlineDetails.setName("Updated Airlines");
        updatedAirlineDetails.setCode("UA");

        Airline updatedAirline = new Airline();
        updatedAirline.setId(1L);
        updatedAirline.setName("Updated Airlines");
        updatedAirline.setCode("UA");

        Mockito.when(airlineService.updateAirline(Mockito.eq(1L), Mockito.any(Airline.class))).thenReturn(updatedAirline);

        mockMvc.perform(put("/api/airlines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAirlineDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Airlines"))
                .andExpect(jsonPath("$.code").value("UA"));
    }

    @Test
    public void testDeleteAirline() throws Exception {
        mockMvc.perform(delete("/api/airlines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(airlineService, Mockito.times(1)).deleteAirline(1L);
    }
}