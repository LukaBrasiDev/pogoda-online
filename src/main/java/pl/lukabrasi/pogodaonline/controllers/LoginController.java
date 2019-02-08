package pl.lukabrasi.pogodaonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukabrasi.pogodaonline.services.UserService;

@Controller
public class LoginController {


    final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("success", "Udało się!");
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            Model model) {
        if (!userService.tryLogin(login, password)) {
            model.addAttribute("error", "Błędne dane");
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        userService.logout();
        return "redirect:/login";
    }


}
