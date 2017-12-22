package controllers.web;

import entities.User;
import entities.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import servicesapi.TokenService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView, String error, String logout) {
        modelAndView.addObject("user",new UserForm());
        if (error != null) {
            modelAndView.addObject("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            modelAndView.addObject("message", "Logged out successfully.");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/authorize",method = RequestMethod.POST)
    public String getUserFromLogin(@ModelAttribute("user") UserForm user) {
        boolean checkAuthentication = tokenService.authenticationUser(user);
        if (checkAuthentication) {
            return "redirect:/welcomeuser";
        } else return "redirect:/login";
    }
}
