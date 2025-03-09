package emt223287.service;



import emt223287.model.Country;
import emt223287.model.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Long country);

    Country addCountry(CountryDto country);

    Country editCountry(Long id, CountryDto country);

    void deleteCountry(Long id);
}
