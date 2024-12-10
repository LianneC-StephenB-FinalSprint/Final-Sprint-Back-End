package com.keyin;

import com.keyin.passenger.PassengerController;
import com.keyin.passenger.PassengerService;
import com.keyin.passenger.Passenger;
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

@WebMvcTest(PassengerController.class)
public class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @Test
    public void testGetAllPassengers() throws Exception {
        when(passengerService.getAllPassengers()).thenReturn(List.of(
                new Passenger("John", "Doe", "123456789"),
                new Passenger("Jane", "Smith", "987654321")
        ));

        mockMvc.perform(get("/api/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].phoneNumber").value("123456789"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].phoneNumber").value("987654321"));

        verify(passengerService).getAllPassengers();
    }

    @Test
    public void testGetPassengerById() throws Exception {
        Passenger passenger = new Passenger("Alice", "Johnson", "555555555");
        when(passengerService.getPassengerById(1L)).thenReturn(passenger);

        mockMvc.perform(get("/api/passengers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Alice"))
                .andExpect(jsonPath("$.lastName").value("Johnson"))
                .andExpect(jsonPath("$.phoneNumber").value("555555555"));

        verify(passengerService).getPassengerById(1L);
    }

    @Test
    public void testCreatePassenger() throws Exception {
        Passenger passenger = new Passenger("Robert", "Brown", "333333333");
        when(passengerService.createPassenger(any(Passenger.class))).thenReturn(passenger);

        String passengerJson = "{\"firstName\":\"Robert\",\"lastName\":\"Brown\",\"phoneNumber\":\"333333333\"}";

        mockMvc.perform(post("/api/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(passengerJson))
                .andExpect(status().isOk()) // Check for HTTP 200 OK response
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Ensure JSON response
                .andExpect(jsonPath("$.firstName").value("Robert"))
                .andExpect(jsonPath("$.lastName").value("Brown"))
                .andExpect(jsonPath("$.phoneNumber").value("333333333"));

        verify(passengerService).createPassenger(any(Passenger.class));
    }

    @Test
    public void testUpdatePassenger() throws Exception {
        Passenger updatedPassenger = new Passenger("Emily", "Clark", "222222222");
        when(passengerService.updatePassenger(eq(1L), any(Passenger.class))).thenReturn(updatedPassenger);

        String updatedPassengerJson = "{\"firstName\":\"Emily\",\"lastName\":\"Clark\",\"phoneNumber\":\"222222222\"}";

        mockMvc.perform(put("/api/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPassengerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Emily"))
                .andExpect(jsonPath("$.lastName").value("Clark"))
                .andExpect(jsonPath("$.phoneNumber").value("222222222"));

        verify(passengerService).updatePassenger(eq(1L), any(Passenger.class));
    }

    @Test
    public void testDeletePassenger() throws Exception {
        mockMvc.perform(delete("/api/passengers/1"))
                .andExpect(status().isOk());

        verify(passengerService).deletePassenger(1L);
    }
}
