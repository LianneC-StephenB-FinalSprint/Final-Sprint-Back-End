package com.keyin.city;

import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Integer id) {
        return cityRepository.findById(id).orElse(null);
    }

    public List<Airport> getAirportsByCity(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        return city.map(City::getAirports).orElse(null);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Integer id, City cityDetails) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isPresent()) {
            City existingCity = cityOptional.get();
            existingCity.setName(cityDetails.getName());
            existingCity.setState(cityDetails.getState());
            existingCity.setPopulation(cityDetails.getPopulation());
            return cityRepository.save(existingCity);
        } else {
            return null;
        }
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }
}