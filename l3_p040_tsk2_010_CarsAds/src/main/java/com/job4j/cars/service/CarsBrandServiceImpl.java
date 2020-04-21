package com.job4j.cars.service;



import com.job4j.cars.dao.CarsBrandRepository;
import com.job4j.cars.entity.CarsBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarsBrandServiceImpl implements CarsBrandService {

    private com.job4j.cars.dao.CarsBrandRepository CarsBrandRepository;

    @Autowired
    public CarsBrandServiceImpl(CarsBrandRepository pCarsBrandRepository) {
        this.CarsBrandRepository = pCarsBrandRepository;
    }

    @Override
    public List<CarsBrand> findAll() {
        return CarsBrandRepository.findAll();
    }


    @Override
    public CarsBrand findById(int theId) {
        Optional<CarsBrand> result = CarsBrandRepository.findById(theId);

        CarsBrand theCarsBrand = null;

        if (result.isPresent()) {
            theCarsBrand = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + theId);
        }

        return theCarsBrand;
    }

    @Override
    public void save(CarsBrand theCarsBrand) {
        CarsBrandRepository.save(theCarsBrand);
    }

    @Override
    public void deleteById(int theId) {
        CarsBrandRepository.deleteById(theId);
    }

}
