package services.authentificate;

import Utils.TokenUtil;
import entities.User;
import entities.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import servicesapi.TokenService;
import configuration.security.TokenAuthentication;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOG = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public String authenticationUser(UserForm user) {
        String login = user.getUsername();
        String password = user.getPassword();

        if (login != null && password != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            if (userDetails != null && userDetails.getUsername().equals(login) && userDetails.getPassword().equals(password)) {
                String token = TokenUtil.createToken(userDetails);

                TokenAuthentication authentication = new TokenAuthentication(userDetails, null, userDetails.getAuthorities());
                authentication.setToken(token);

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                LOG.info("Create token: " + token);
                return token;
            }
        }

        return null;
    }
}
