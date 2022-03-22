package com.test_module_04.service;

import com.test_module_04.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> FindAllCities();

    Optional<City> findById(Long id);

    City save(City city);

    void remove(Long id);
}
