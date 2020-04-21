package com.job4j.cars.service;

import com.job4j.cars.dao.CarsDriveUnitRepository;
import com.job4j.cars.entity.CarsDriveUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarsDriveUnitServiceImpl implements CarsDriveUnitService {

    private CarsDriveUnitRepository carsDriveUnitRepository;

    @Autowired
    public CarsDriveUnitServiceImpl(CarsDriveUnitRepository pCarsDriveUnitRepository) {
        this.carsDriveUnitRepository = pCarsDriveUnitRepository;
    }

    @Override
    public List<CarsDriveUnit> findAll() {
        return carsDriveUnitRepository.findAll();
    }


    @Override
    public CarsDriveUnit findById(int pId) {
        Optional<CarsDriveUnit> result = carsDriveUnitRepository.findById(pId);

        CarsDriveUnit pCarsDriveUnit = null;

        if (result.isPresent()) {
            pCarsDriveUnit = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return pCarsDriveUnit;
    }

    @Override
    public void save(CarsDriveUnit pCarsBodyType) {
        carsDriveUnitRepository.save(pCarsBodyType);
    }

    @Override
    public void deleteById(int pId) {
        carsDriveUnitRepository.deleteById(pId);
    }

}
