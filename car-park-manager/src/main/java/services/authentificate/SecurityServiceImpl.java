package services.authentificate;

import Utils.TokenUtil;
import configuration.security.TokenAuthentication;
import entities.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import servicesapi.SecurityService;
import servicesapi.TokenService;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenService tokenService;

    /**
     * Find user in context.
     * @return user's username if user logged in
     * @return null if user didn't log in
     */
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    /**
     * Do auto login in application.
     * @param username String value
     * @param password String value
     */
    @Override
    public void autoLogin(String username, String password) {
        UserForm userForm = new UserForm();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        userForm.setUsername(userDetails.getUsername());
        userForm.setPassword(userDetails.getPassword());
        tokenService.authenticationUser(userForm);
        TokenAuthentication authentication = new TokenAuthentication(userDetails,
                null, userDetails.getAuthorities());
        authentication.setToken(TokenUtil.createToken(userDetails));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
