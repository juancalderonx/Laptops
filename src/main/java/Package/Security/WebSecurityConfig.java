package Package.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getAllLaptops").permitAll()
                //Esta línea me permite quitar la autenticación de una URL
                .antMatchers("/getLaptop/{id}").hasRole("ADMIN")
                .antMatchers("/deleteAllLaptops").hasRole("ADMIN")
                //Línea que dice que todas las peticiones deben estar autenticadas.
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("Juan").password(passwordEncoder().encode("2311")).roles("ADMIN")
                .and()
                .withUser("Gabo").password(passwordEncoder().encode("0911")).roles("ADMIN")
                .and()
                .withUser("Joaco").password(passwordEncoder().encode("0011")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
