package pl.lukabrasi.pogodaonline.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lukabrasi.pogodaonline.weather.services.WeatherLogService;

@Controller
public class HistoryController {

    final WeatherLogService weatherLogService;

    @Autowired
    public HistoryController(WeatherLogService weatherLogService) {
        this.weatherLogService = weatherLogService;
    }




    @GetMapping("/history")
    public String histoty(Model model) {
        model.addAttribute("logs", weatherLogService.getWeatherLogFromUser());
        return "history";
    }
}
