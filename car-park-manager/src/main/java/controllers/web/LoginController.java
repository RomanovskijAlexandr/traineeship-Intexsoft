package controllers.web;

import entities.User;
import entities.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import servicesapi.TokenService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TokenService tokenService;

    HttpHeaders responseHeaders;

    /**
     * Get method. Return view for Login.
     * @param modelAndView
     * @param error
     * @param logout
     * @return ModelAndView
     */
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

    /**
     * Post method. This method get headers and check user's authentification
     * @param user UserForm value
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/authorize",method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public ResponseEntity<String> authenticationUser(@RequestBody UserForm user) {
        String tokenAuthentication = tokenService.authenticationUser(user);
        if (tokenAuthentication != null) {
            responseHeaders = new HttpHeaders();
            responseHeaders.add("X-Auth-Token",tokenAuthentication);
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
