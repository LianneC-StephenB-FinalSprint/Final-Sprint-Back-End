package com.keyin;

import com.keyin.city.CityController;
import com.keyin.city.CityService;
import com.keyin.city.City;

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

@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Test
    public void testGetAllCities() throws Exception {
        when(cityService.getAllCities()).thenReturn(List.of(
                new City("New York", "USA", 8419000),
                new City("Los Angeles", "USA", 3980000)
        ));

        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("New York"))
                .andExpect(jsonPath("$[0].country").value("USA"))
                .andExpect(jsonPath("$[0].population").value(8419000))
                .andExpect(jsonPath("$[1].name").value("Los Angeles"))
                .andExpect(jsonPath("$[1].country").value("USA"))
                .andExpect(jsonPath("$[1].population").value(3980000));

        verify(cityService).getAllCities();
    }

    @Test
    public void testGetCityById() throws Exception {
        City city = new City("San Francisco", "USA", 883305);
        when(cityService.getCityById(1)).thenReturn(city);

        mockMvc.perform(get("/api/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("San Francisco"))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.population").value(883305));

        verify(cityService).getCityById(1);
    }

    @Test
    public void testCreateCity() throws Exception {
        City city = new City("Chicago", "USA", 2716000);
        when(cityService.createCity(any(City.class))).thenReturn(city);

        String cityJson = "{\"name\":\"Chicago\",\"country\":\"USA\",\"population\":2716000}";

        mockMvc.perform(post("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chicago"))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.population").value(2716000));

        verify(cityService).createCity(any(City.class));
    }

    @Test
    public void testUpdateCity() throws Exception {
        City updatedCity = new City("San Diego", "USA", 1423851);
        when(cityService.updateCity(eq(1), any(City.class))).thenReturn(updatedCity);

        String updatedCityJson = "{\"name\":\"San Diego\",\"country\":\"USA\",\"population\":1423851}";

        mockMvc.perform(put("/api/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("San Diego"))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.population").value(1423851));

        verify(cityService).updateCity(eq(1), any(City.class));
    }

    @Test
    public void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/api/cities/1"))
                .andExpect(status().isOk());

        verify(cityService).deleteCity(1);
    }
}
