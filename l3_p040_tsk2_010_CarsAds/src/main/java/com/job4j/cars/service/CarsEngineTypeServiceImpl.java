package com.job4j.cars.service;

import com.job4j.cars.dao.CarsEngineTypeRepository;
import com.job4j.cars.entity.CarsEngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CarsEngineTypeServiceImpl implements CarsEngineTypeService {

    private CarsEngineTypeRepository carsEngineTypeRepository;

    @Autowired
    public CarsEngineTypeServiceImpl(CarsEngineTypeRepository pCarsBodyTypeRepository) {
        this.carsEngineTypeRepository = pCarsBodyTypeRepository;
    }

    @Override
    public List<CarsEngineType> findAll() {
        return carsEngineTypeRepository.findAll();
    }


    @Override
    public CarsEngineType findById(int pId) {
        Optional<CarsEngineType> result = carsEngineTypeRepository.findById(pId);

        CarsEngineType pCarsBodyType = null;

        if (result.isPresent()) {
            pCarsBodyType = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return pCarsBodyType;
    }

    @Override
    public void save(CarsEngineType pCarsBodyType) {
        carsEngineTypeRepository.save(pCarsBodyType);
    }

    @Override
    public void deleteById(int pId) {
        carsEngineTypeRepository.deleteById(pId);
    }

}
