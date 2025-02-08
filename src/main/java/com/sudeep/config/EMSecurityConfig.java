package com.sudeep.config;

import com.sudeep.service.EmployeeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EMSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
    /*    UserDetails manager = User
                .withUsername("Hemansh")
                .password(passwordEncoder().encode("Hemansh@308"))
                .roles("MANAGER")
                .build();
        UserDetails hr = User.withUsername("Kavya")
                .password(passwordEncoder().encode("Kavya@308"))
                .roles("HR", "MANAGER")
                .build();
        UserDetails employee = User.withUsername("Sudeep")
                .password(passwordEncoder().encode("Sudeep@308"))
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(manager, hr, employee);*/
        return new EmployeeUserDetailsService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/employees/welcome", "/employees/create").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/employees/**").authenticated()
                .and().
                httpBasic()
                .and().build();

    }

    //below method for latest versions
/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                req -> req.requestMatchers("/non-secure").permitAll()
                        .requestMatchers("/welcome").authenticated()
        ).formLogin(Customizer.withDefaults()).build();


    }*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
