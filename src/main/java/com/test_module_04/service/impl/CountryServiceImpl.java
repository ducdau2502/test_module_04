package com.test_module_04.service.impl;

import com.test_module_04.model.Country;
import com.test_module_04.repository.ICountryRepository;
import com.test_module_04.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    ICountryRepository countryRepository;

    @Override
    public Iterable<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void remove(Long id) {
        countryRepository.deleteById(id);
    }
}
