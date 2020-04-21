package com.job4j.cars.service;

import com.job4j.cars.dao.CarsTransmissionRepository;
import com.job4j.cars.entity.CarsTransmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CarsTransmissionServiceImpl implements CarsTransmissionService {

    private CarsTransmissionRepository carsTransmissionRepository;

    @Autowired
    public CarsTransmissionServiceImpl(CarsTransmissionRepository pCarsTransmissionRepository) {
        this.carsTransmissionRepository = pCarsTransmissionRepository;
    }

    @Override
    public List<CarsTransmission> findAll() {
        return carsTransmissionRepository.findAll();
    }


    @Override
    public CarsTransmission findById(int pId) {
        Optional<CarsTransmission> result = carsTransmissionRepository.findById(pId);

        CarsTransmission pCarsBodyType = null;

        if (result.isPresent()) {
            pCarsBodyType = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return pCarsBodyType;
    }

    @Override
    public void save(CarsTransmission pCarsBodyType) {
        carsTransmissionRepository.save(pCarsBodyType);
    }

    @Override
    public void deleteById(int pId) {
        carsTransmissionRepository.deleteById(pId);
    }

}
