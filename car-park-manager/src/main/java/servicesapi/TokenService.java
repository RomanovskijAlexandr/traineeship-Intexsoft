package servicesapi;

import entities.User;
import entities.UserForm;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    public boolean authenticationUser(UserForm user);
}
