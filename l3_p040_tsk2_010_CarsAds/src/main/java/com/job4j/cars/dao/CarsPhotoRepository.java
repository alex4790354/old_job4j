package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsPhotoRepository extends JpaRepository<CarsPhoto, Integer> {

    // add a method to sort by last name
    public List<CarsPhoto> findByAdId(int theAdId);


}
