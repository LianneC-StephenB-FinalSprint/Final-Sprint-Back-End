package com.keyin;

import com.keyin.city.City;
import com.keyin.city.CityController;
import com.keyin.city.CityService;

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
                new City("New York", "New York", 8419000),
                new City("Los Angeles", "California", 3980000)
        ));

        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("New York"))
                .andExpect(jsonPath("$[0].state").value("New York"))  // Change country to state
                .andExpect(jsonPath("$[0].population").value(8419000))
                .andExpect(jsonPath("$[1].name").value("Los Angeles"))
                .andExpect(jsonPath("$[1].state").value("California"))  // Change country to state
                .andExpect(jsonPath("$[1].population").value(3980000));

        verify(cityService).getAllCities();
    }

    @Test
    public void testGetCityById() throws Exception {
        City city = new City("San Francisco", "California", 883305);
        when(cityService.getCityById(1L)).thenReturn(city);

        mockMvc.perform(get("/api/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("San Francisco"))
                .andExpect(jsonPath("$.state").value("California"))  // Change country to state
                .andExpect(jsonPath("$.population").value(883305));

        verify(cityService).getCityById(1L);
    }

    @Test
    public void testCreateCity() throws Exception {
        City city = new City("Chicago", "Illinois", 2716000);
        when(cityService.createCity(any(City.class))).thenReturn(city);

        String cityJson = "{\"name\":\"Chicago\",\"state\":\"Illinois\",\"population\":2716000}";  // Change country to state

        mockMvc.perform(post("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chicago"))
                .andExpect(jsonPath("$.state").value("Illinois"))  // Change country to state
                .andExpect(jsonPath("$.population").value(2716000));

        verify(cityService).createCity(any(City.class));
    }

    @Test
    public void testUpdateCity() throws Exception {
        City updatedCity = new City("San Diego", "California", 1423851);
        when(cityService.updateCity(eq(1L), any(City.class))).thenReturn(updatedCity);

        String updatedCityJson = "{\"name\":\"San Diego\",\"state\":\"California\",\"population\":1423851}";  // Change country to state

        mockMvc.perform(put("/api/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("San Diego"))
                .andExpect(jsonPath("$.state").value("California"))  // Change country to state
                .andExpect(jsonPath("$.population").value(1423851));

        verify(cityService).updateCity(eq(1L), any(City.class));
    }

    @Test
    public void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/api/cities/1"))
                .andExpect(status().isOk());

        verify(cityService).deleteCity(1L);
    }
}