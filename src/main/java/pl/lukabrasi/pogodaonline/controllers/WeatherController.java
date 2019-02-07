package pl.lukabrasi.pogodaonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukabrasi.pogodaonline.services.WeatherLogService;

@Controller
public class WeatherController {

    final WeatherLogService weatherLogService;

    @Autowired
    public WeatherController(WeatherLogService weatherLogService) {
        this.weatherLogService = weatherLogService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String index(@RequestParam("cityName") String cityName, Model model) {
        model.addAttribute("weather", weatherLogService.getCurrentWeather(cityName));
        model.addAttribute("forecast", weatherLogService.getWeatherForecast(cityName));
        return "index";
    }
}