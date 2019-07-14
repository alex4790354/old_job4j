package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cars21.models.CarAd;


@Controller
public class CarAdsController {

   //final List<CarAd> allCarsAds = new CopyOnWriteArrayList<>();

   @RequestMapping(value = "/carsads", method = RequestMethod.GET)
   public String showItem(ModelMap model) {
      //model.addAttribute("carsAds", this.allCarsAds);
      return "/cars21/carsAds.jsp";
   }

   @RequestMapping(value = "/carsads", method = RequestMethod.POST)
   public String addItem(@ModelAttribute CarAd carAd) {
      //this.allCarsAds.add(CarAd);
      return "redirect:/cars21/carsAds.jsp";
   }

}