package configuration.webconfigurations;


import configuration.provider.CustomAuthenticationProvider;
import configuration.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"validator", "configuration.provider", "services.authentificate" , "servicesapi", "controllers.web"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login/authorize").permitAll()
                .antMatchers("/buses").authenticated()
                .antMatchers("/drivers").authenticated()
                .antMatchers("/rotes").authenticated()
                .antMatchers("/registration").permitAll()
                .antMatchers("/welcomeuser").authenticated()
                .antMatchers("/welcomeadmin").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated();

        http.addFilterBefore(new TokenAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
