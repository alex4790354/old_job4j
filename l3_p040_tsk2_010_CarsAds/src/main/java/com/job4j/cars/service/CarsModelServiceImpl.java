package com.job4j.cars.service;

import com.job4j.cars.dao.CarsModelRepository;
import com.job4j.cars.entity.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarsModelServiceImpl implements CarsModelService {

	private CarsModelRepository carsModelRepository;

	@Autowired
	public CarsModelServiceImpl(CarsModelRepository pCarsModelRepository) {
		carsModelRepository = pCarsModelRepository;
	}

	@Override
	public List<CarsModel> findAll() {
		return carsModelRepository.findAll();
	}

	@Override
	public List<CarsModel> findByBrandId(int pBrandId) {
		return carsModelRepository.findByBrandId(pBrandId);
	}

	@Override
	public CarsModel findById(int theId) {
		Optional<CarsModel> result = carsModelRepository.findById(theId);

		CarsModel theCustomer = null;

		if (result.isPresent()) {
			theCustomer = result.get();
		} else {
			// we didn't find the customer
			throw new RuntimeException("Did not find customer id - " + theId);
		}

		return theCustomer;
	}

	@Override
	public void save(CarsModel theCustomer) {
		carsModelRepository.save(theCustomer);
	}

	@Override
	public void deleteById(int theId) {
		carsModelRepository.deleteById(theId);
	}

}

