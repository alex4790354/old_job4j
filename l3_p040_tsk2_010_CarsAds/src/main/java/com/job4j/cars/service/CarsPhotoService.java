package com.job4j.cars.service;

import com.job4j.cars.entity.CarsPhoto;

import java.util.List;


public interface CarsPhotoService {

	public List<CarsPhoto> findAll();

	public List<CarsPhoto> findByAdId(int theAdId);
	
	public CarsPhoto findById(int theId);

	//public CarsPhoto createNewPhoto(int pAdId);
	
	public void save(CarsPhoto theCarsPhoto);
	
	public void deleteById(int theId);

	
}
