package configuration.provider;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import repositories.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * This method authenticate user
     * @param authentication Authentication value
     * @return user's authentification, if authetification is correct
     * @return null, if authetification is uncorrect
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        for (User user:userRepository.findAll()) {
            if(user.getUsername().equals(name) && user.getPassword().equals(password)){
                return new UsernamePasswordAuthenticationToken(name, password,
                        userDetailsService.loadUserByUsername(name).getAuthorities());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
