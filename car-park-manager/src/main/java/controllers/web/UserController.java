package controllers.web;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.authentificate.SecurityServiceImpl;
import servicesapi.SecurityService;
import servicesapi.UserService;
import validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    /**
     * Get method. Add attribute in model.
     * @param model Model value
     * @return view name registration
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * Post method. Check input user's data and register in application.
     * @param user User value
     * @param bindingResult BindingResult value
     * @param model Model value
     * @return view name registration if input data is uncorrect
     * @return view name welcomeuser if input data is correct
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    errors.add(fieldError.getCode());
                }
            }
            model.addAttribute("error",errors);
            return "registration";
        }else {
            userService.save(user);
            securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
            return "welcomeuser";
        }
    }

    /**
     *
     * @param model Model value
     * @return view name welcomeuser
     */
    @RequestMapping(value = "/welcomeuser", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcomeuser";
    }

    /**
     *
     * @param model Model value
     * @return view name welcomeadmin
     */
    @RequestMapping(value = "/welcomeadmin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "welcomeadmin";
    }
}
