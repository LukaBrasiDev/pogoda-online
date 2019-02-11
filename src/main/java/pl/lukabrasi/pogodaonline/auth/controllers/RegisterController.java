package pl.lukabrasi.pogodaonline.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lukabrasi.pogodaonline.auth.forms.RegisterForm;
import pl.lukabrasi.pogodaonline.auth.services.UserService;

@Controller
public class RegisterController {

    final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

/*    @GetMapping("/register")
    public String login() {
        return "register";
    }

    @PostMapping("/register")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email,
                        Model model) {
        if (!userService.addUser(login, password, email)) {
            model.addAttribute("error", "Nick jest zajęty!");
            return "register";
        }
        return "redirect:/login"; // udalo sie
    }*/

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm,
                           Model model) {

        if (!userService.registerUser(registerForm)) {
            model.addAttribute("info", "Login jest już zajęty!");
            return "register";
        }
        userService.registerUser(registerForm);
        return "redirect:/login";
    }


}