package com.job4j.cars.service;

import com.job4j.cars.dao.CarsBodyTypeRepository;
import com.job4j.cars.entity.CarsBodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CarsBodyTypeServiceImpl implements CarsBodyTypeService {

    private CarsBodyTypeRepository carsBodyTypeRepository;

    @Autowired
    public CarsBodyTypeServiceImpl(CarsBodyTypeRepository pCarsBodyTypeRepository) {
        this.carsBodyTypeRepository = pCarsBodyTypeRepository;
    }

    @Override
    public List<CarsBodyType> findAll() {
        return carsBodyTypeRepository.findAll();
    }


    @Override
    public CarsBodyType findById(int pId) {
        Optional<CarsBodyType> result = carsBodyTypeRepository.findById(pId);

        CarsBodyType pCarsBodyType = null;

        if (result.isPresent()) {
            pCarsBodyType = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return pCarsBodyType;
    }

    @Override
    public void save(CarsBodyType pCarsBodyType) {
        carsBodyTypeRepository.save(pCarsBodyType);
    }

    @Override
    public void deleteById(int pId) {
        carsBodyTypeRepository.deleteById(pId);
    }

}
