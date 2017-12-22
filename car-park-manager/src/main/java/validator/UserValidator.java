package validator;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import servicesapi.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 4) {
            errors.rejectValue("username", "Username must be more then 4 symbols.");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Such username already exists.");
        }

      /*  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field password is required.");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 8) {
            errors.rejectValue("password", "Password must be from 4 to 8 characters.");
        }*/

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Password don't match.");
        }
    }
}
