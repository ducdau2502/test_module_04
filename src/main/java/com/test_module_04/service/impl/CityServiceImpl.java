package com.test_module_04.service.impl;

import com.test_module_04.model.City;
import com.test_module_04.repository.ICityRepository;
import com.test_module_04.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    ICityRepository cityRepository;

    @Override
    public Iterable<City> FindAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
    cityRepository.deleteById(id);
    }
}
