package configuration.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken {
    private String token;

    /**
     *
     * @param principal first Object value
     * @param credentials second Object value
     * @return new object TokenAuthentication value
     */
    public TokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials);

    }

    /**
     *
     * @param principal first Object value
     * @param credentials second Object value
     * @param authorities third Collection<? extends GrantedAuthority> value
     * @return new object TokenAuthentication value
     */
    public TokenAuthentication(Object principal, Object credentials,
                               Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;

    }
}
