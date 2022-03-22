package com.test_module_04.controller;

import com.test_module_04.model.City;
import com.test_module_04.model.Country;
import com.test_module_04.service.ICityService;
import com.test_module_04.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showCities() {
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable<City> cities = cityService.FindAllCities();
        if (!cities.iterator().hasNext()) {
            modelAndView.addObject("message", "List cities is empty");
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView sava(@Valid @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message", "Create failed");
            modelAndView.addObject("color", "Red");
            modelAndView.addObject("city", city);
        } else {
            modelAndView.addObject("message", "Create Successful");
            modelAndView.addObject("color", "Blue");
            modelAndView.addObject("city", new City());
            cityService.save(city);
        }
        modelAndView.addObject("countries", countryService.findAllCountries());
        return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        cityService.remove(id);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<City> city = cityService.findById(id);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<City> city = cityService.findById(id);
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@Valid @ModelAttribute City city, BindingResult bindingResult, @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        city.setId(id);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message", "Update failed");
            modelAndView.addObject("color", "Red");
        } else {
            cityService.save(city);
            modelAndView.addObject("message", "Update Successful");
            modelAndView.addObject("color", "Blue");
        }
        modelAndView.addObject("city", city);
        modelAndView.addObject("countries", countryService.findAllCountries());
        return modelAndView;
    }
}
