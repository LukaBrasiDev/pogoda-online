package pl.lukabrasi.pogodaonline.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lukabrasi.pogodaonline.auth.services.UserSession;
import pl.lukabrasi.pogodaonline.weather.forms.WeatherForm;
import pl.lukabrasi.pogodaonline.weather.services.WeatherLogService;

@Controller
public class EditController {


    final WeatherLogService weatherLogService;
    final UserSession userSession;

    @Autowired

    public EditController(WeatherLogService weatherLogService, UserSession userSession) {
        this.weatherLogService = weatherLogService;
        this.userSession = userSession;
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       Model model) {
        model.addAttribute("log", weatherLogService.getWeatherLogEntityById(id));
        model.addAttribute("weatherForm", new WeatherForm());
        return "edit";
    }


    @PostMapping("/edit/{id}")
    public String register(
            @PathVariable int id,
            @ModelAttribute WeatherForm weatherForm) {

        weatherLogService.updateWeatherLogEntity(id, weatherForm);

        return "redirect:/history";

    }


}
