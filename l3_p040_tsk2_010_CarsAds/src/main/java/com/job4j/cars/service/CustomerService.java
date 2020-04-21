package com.job4j.cars.service;

import com.job4j.cars.entity.Customer;
import java.util.List;


public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theEmployee);
	
	public void deleteById(int theId);
	
}
