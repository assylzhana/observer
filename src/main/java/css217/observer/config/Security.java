package css217.observer.config;
import css217.observer.services.InvestorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security
{
    @Bean
    public InvestorService investorService()
    {
        return new InvestorService();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(investorService())
                .passwordEncoder(passwordEncoder());

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(flc -> flc.loginProcessingUrl("/auth")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/sign-in")
                .defaultSuccessUrl("/profile",true)
                .failureUrl("/sign-in?error"));

        httpSecurity.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/sign-in"));


        return httpSecurity.build();
    }
}