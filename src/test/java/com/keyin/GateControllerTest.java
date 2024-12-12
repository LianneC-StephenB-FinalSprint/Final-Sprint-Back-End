package com.keyin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.gate.Gate;
import com.keyin.gate.GateController;
import com.keyin.gate.GateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GateController.class)
public class GateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GateService gateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllGates() throws Exception {
        // Create mock data
        Gate gate1 = new Gate(1L, "Gate A1", "Terminal 1");
        Gate gate2 = new Gate(2L, "Gate B2", "Terminal 2");
        List<Gate> mockGates = List.of(gate1, gate2);

        // Mock service call
        Mockito.when(gateService.getAllGates()).thenReturn(mockGates);

        // Perform GET request and assert results
        mockMvc.perform(get("/api/gates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Gate A1"))
                .andExpect(jsonPath("$[1].name").value("Gate B2"));
    }

    @Test
    public void testAddGate() throws Exception {
        // Create mock data
        Gate newGate = new Gate(null, "Gate C3", "Terminal 3");
        Gate savedGate = new Gate(3L, "Gate C3", "Terminal 3");

        // Mock service call
        Mockito.when(gateService.addGate(Mockito.any(Gate.class))).thenReturn(savedGate);

        // Perform POST request and assert results
        mockMvc.perform(post("/api/gates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newGate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("Gate C3"));
    }

    @Test
    public void testGetGateById() throws Exception {
        // Create mock data
        Gate mockGate = new Gate(1L, "Gate A1", "Terminal 1");

        // Mock service call
        Mockito.when(gateService.getGateById(1L)).thenReturn(mockGate);

        // Perform GET request and assert results
        mockMvc.perform(get("/api/gates/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gate A1"));
    }

    @Test
    public void testUpdateGate() throws Exception {
        // Create mock data
        Gate updatedGateDetails = new Gate(null, "Gate A2", "Terminal 1");
        Gate updatedGate = new Gate(1L, "Gate A2", "Terminal 1");

        // Mock service call
        Mockito.when(gateService.updateGate(Mockito.eq(1L), Mockito.any(Gate.class))).thenReturn(updatedGate);

        // Perform PUT request and assert results
        mockMvc.perform(put("/api/gates/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedGateDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gate A2"));
    }

    @Test
    public void testDeleteGate() throws Exception {
        // Perform DELETE request and assert results
        mockMvc.perform(delete("/api/gates/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify service call
        Mockito.verify(gateService, Mockito.times(1)).deleteGate(1L);
    }
}