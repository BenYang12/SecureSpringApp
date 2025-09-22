package com.benyang12.securespringapp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;

//Manage who is allowed to do what and who the users are

@Configuration
@EnableWebSecurity //activate Spring Security for web app
public class WebSecurityConfig {
    //permitAll() means "let everyone access this resource"

    //SecurityFilterChain bean should define which URL paths should be secured and which ones should not
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll() //anyone can access home page
                        .anyRequest().authenticated() //all  requests other than home require the user to be logged in


                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()//everyone should be able to access log in page
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    //this bean sets up in-memory user store with a single user
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .password("abc123") //Todo: implement database in future
                        .username("John Doe")//hardcode credentials for this simple project
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}