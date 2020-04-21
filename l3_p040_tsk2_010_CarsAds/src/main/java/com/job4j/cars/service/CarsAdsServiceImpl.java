package com.job4j.cars.service;


import com.job4j.cars.dao.CarsAdsRepository;
import com.job4j.cars.entity.CarsAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.util.List;

@Service
public class CarsAdsServiceImpl implements CarsAdsService {

    private CarsAdsRepository carsAdsRepository;

    @Autowired
    public CarsAdsServiceImpl(CarsAdsRepository carsAdsRepository) {
        this.carsAdsRepository = carsAdsRepository;
    }

    @Override
    public List<CarsAd> findAll() {
        return carsAdsRepository.findAll();
    }


    @Override
    public CarsAd findById(int theId) {
        Optional<CarsAd> result = carsAdsRepository.findById(theId);

        CarsAd theCarsAds = null;

        if (result.isPresent()) {
            theCarsAds = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + theId);
        }

        return theCarsAds;
    }

    @Override
    public void save(CarsAd theEmployee) {
        carsAdsRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        carsAdsRepository.deleteById(theId);
    }

}
