package pl.lukabrasi.pogodaonline.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukabrasi.pogodaonline.auth.services.UserSession;
import pl.lukabrasi.pogodaonline.weather.services.WeatherLogService;

@Controller
public class WeatherController {

    final WeatherLogService weatherLogService;
    final UserSession userSession;

    @Autowired
    public WeatherController(UserSession userSession, WeatherLogService weatherLogService) {
        this.userSession = userSession;
        this.weatherLogService = weatherLogService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!userSession.isLogin()) {
            return "redirect:/login";
        }

        return "index";
    }


    @PostMapping("/")
    public String index(@RequestParam("cityName") String cityName,
                        Model model) {
        if (!userSession.isLogin()) {
            return "redirect:/login";
        }
        model.addAttribute("weather", weatherLogService.getCurrentWeather(cityName));
        model.addAttribute("forecast", weatherLogService.getWeatherForecast(cityName));
        return "index";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("error", "Niepoprawna nazwa miasta!");
        return "redirect:/";
    }
}
