package emt223287.service.impl;

import emt223287.model.Country;
import emt223287.model.dto.CountryDto;
import emt223287.repository.CountryRepository;
import emt223287.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    @Override
    public Country addCountry(CountryDto country) {
        Country newCountry = new Country();

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }

    @Override
    public Country editCountry(Long id, CountryDto country) {
        Country newCountry = countryRepository.findById(id).orElse(null);

        if (newCountry == null) {
            return null;
        }

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
