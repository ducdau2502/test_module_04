package com.test_module_04.service;

import com.test_module_04.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAllCountries();

    Optional<Country> findById(Long id);

    Country save(Country country);

    void remove(Long id);
}
