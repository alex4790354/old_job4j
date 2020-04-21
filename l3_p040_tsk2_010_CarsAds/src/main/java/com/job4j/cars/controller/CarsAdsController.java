package com.job4j.cars.controller;

import com.job4j.cars.entity.*;
import com.job4j.cars.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carsad")
public class CarsAdsController {

	private CarsAdsService carsAdsService;
	private CarsBrandService carsBrandService;
	private CarsModelService carsModelService;
	private CarsBodyTypeService carsBodyTypeService;
	private CarsTransmissionService carsTransmissionService;
	private CarsEngineTypeService carsEngineTypeService;
	private CarsDriveUnitService carsDriveUnitService;

	public CarsAdsController(CarsAdsService pCarsAdsService,
							 CarsBrandService pCarsBrandService,
							 CarsModelService pCarsModelService,
							 CarsBodyTypeService pCarsBodyTypeService,
							 CarsTransmissionService pCarsTransmissionService,
							 CarsEngineTypeService pCarsEngineTypeService,
							 CarsDriveUnitService pCarsDriveUnitService) {
		this.carsAdsService = pCarsAdsService;
		this.carsBrandService = pCarsBrandService;
		this.carsModelService = pCarsModelService;
		this.carsBodyTypeService = pCarsBodyTypeService;
		this.carsTransmissionService = pCarsTransmissionService;
		this.carsEngineTypeService = pCarsEngineTypeService;
		this.carsDriveUnitService = pCarsDriveUnitService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model pModel) {
		
		// get customers from db
		// add to the spring model
		pModel.addAttribute("carsAds", carsAdsService.findAll());
		
		return "carsads/list-ads";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model pModel) {
		
		// create model attribute to bind form data
		//CarsAd pCarsAd = new CarsAd();
		pModel.addAttribute("carsAd", new CarsAd());
        pModel.addAttribute("carsBrands", carsBrandService.findAll());
		pModel.addAttribute("carsModels", carsModelService.findAll());
		pModel.addAttribute("carsBodyTypes", carsBodyTypeService.findAll());
		pModel.addAttribute("carsTransmissions", carsTransmissionService.findAll());
		pModel.addAttribute("carsEngineTypes", carsEngineTypeService.findAll());
		pModel.addAttribute("carsDriveUnit", carsDriveUnitService.findAll());
		
		return "carsads/ads-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("carsAdId") int pId, Model pModel) {
		
		// get the customer from the service
		// set customer as a model attribute to pre-populate the form
		CarsAd carsAd = carsAdsService.findById(pId);
		pModel.addAttribute("carsAd", carsAd);
		pModel.addAttribute("carsBrands", carsBrandService.findAll());
		pModel.addAttribute("carsModels", carsModelService.findByBrandId(carsAd.getCarsBrandId()));
		pModel.addAttribute("carsBodyTypes", carsBodyTypeService.findAll());
		pModel.addAttribute("carsTransmissions", carsTransmissionService.findAll());
		pModel.addAttribute("carsEngineTypes", carsEngineTypeService.findAll());
		pModel.addAttribute("carsDriveUnits", carsDriveUnitService.findAll());
		
		// send over to our form
		return "carsads/ads-form";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("carsad") CarsAd pCarsAd) {
		
		// save p customer
		carsAdsService.save(pCarsAd);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/carsad/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("carsAdId") int pId) {
		
		// delete the customer
		carsAdsService.deleteById(pId);
		
		// redirect to /carsjpa/list
		return "redirect:/carsad/list";
	}

}


