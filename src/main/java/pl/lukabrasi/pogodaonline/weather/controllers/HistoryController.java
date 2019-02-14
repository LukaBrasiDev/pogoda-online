package pl.lukabrasi.pogodaonline.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lukabrasi.pogodaonline.auth.services.UserSession;
import pl.lukabrasi.pogodaonline.weather.services.WeatherLogService;

@Controller
public class HistoryController {

    final WeatherLogService weatherLogService;
    final UserSession userSession;

    @Autowired
    public HistoryController(WeatherLogService weatherLogService, UserSession userSession) {
        this.weatherLogService = weatherLogService;
        this.userSession = userSession;
    }


    @GetMapping("/history")
    public String histoty(Model model) {
        model.addAttribute("logs", weatherLogService.getWeatherLogFromUser());
        return "history";
    }


/*    @DeleteMapping(value = "/history/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        weatherLogService.deleteWeatherLogEntityById(id);
        return "redirect:/history";
    }*/

    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") int id) {

        weatherLogService.deleteWeatherLogEntityById(id);
        weatherLogService.getWeatherLogFromUser();

        return "redirect:/history";
    }


/*  @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWeatherLogEntity(@PathVariable("id") Integer id) {
        weatherLogService.deleteWeatherLogEntity(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }*/



/*    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }*/


}
