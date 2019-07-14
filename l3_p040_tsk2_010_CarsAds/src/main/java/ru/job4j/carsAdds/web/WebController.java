package ru.job4j.carsAdds.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.carsAdds.modles.CarAd;
import ru.job4j.carsAdds.repository.CarAdsServiceInterf;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    CarAdsServiceInterf carAdsService;

    @RequestMapping("/showAdds")
    public String findAllAds(Model model) {

        List<CarAd> allCarsAds = (List<CarAd>) carAdsService.findAll();

        model.addAttribute("allCarsAds", allCarsAds);

        return "showAds";
    }

}
