package com.job4j.cars.service;

import com.job4j.cars.entity.CarsPhoto;
import com.job4j.cars.dao.CarsPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarsPhotoServiceImpl implements CarsPhotoService {

    private CarsPhotoRepository carsPhotoRepository;

    @Autowired
    public CarsPhotoServiceImpl(CarsPhotoRepository pCarsPhotoRepository) {
        this.carsPhotoRepository = pCarsPhotoRepository;
    }

    @Override
    public List<CarsPhoto> findAll() {
        return carsPhotoRepository.findAll();
    }

    @Override
    public List<CarsPhoto> findByAdId(int theAdId) {
        List<CarsPhoto> resultOptional = carsPhotoRepository.findByAdId(theAdId);

        List<CarsPhoto> result = new ArrayList<>();

        if (resultOptional == null) {
            result.add(new CarsPhoto(0, null));
        } else {
            result = resultOptional;
        }

        return result;
    }

    @Override
    public CarsPhoto findById(int pId) {
        Optional<CarsPhoto> result = carsPhotoRepository.findById(pId);

        CarsPhoto vCarsPhoto = null;

        if (result.isPresent()) {
            vCarsPhoto = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return vCarsPhoto;
    }

    /*@Override
    public CarsPhoto createNewPhoto(int pAdId) {
        CarsPhoto carsPhoto = new CarsPhoto(pAdId);
        carsPhotoRepository.save(carsPhoto);
        return carsPhoto;
    }*/

    @Override
    public void save(CarsPhoto pCarsPhoto) {
        carsPhotoRepository.save(pCarsPhoto);
    }

    @Override
    public void deleteById(int pId) {
        carsPhotoRepository.deleteById(pId);
    }

}
