package com.capitol.pruebatecnicacapitol.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager usersdetails() throws Exception{
        List<UserDetails> users = List.of(
                User.withUsername("spock")
                        .password("{noop}vulcano")
                        .roles("USERS")
                        .build(),
                User.withUsername("kirk")
                        .password("{noop}enterprise")
                        .roles("OPERATOR")
                        .build(),
                User.withUsername("sheridan")
                        .password("{noop}babylon5")
                        .roles("USERS", "ADMIN")
                        .build()
        );
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/spaceships").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/spaceships/").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
