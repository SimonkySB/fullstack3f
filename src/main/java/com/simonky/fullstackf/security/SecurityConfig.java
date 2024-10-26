package com.simonky.fullstackf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/libros/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/libros/**").hasRole( "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/libros/**").hasRole( "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/libros/**").hasRole( "ADMIN")
                .anyRequest()
                .authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler())
            )
            .httpBasic(withDefaults())
            .build()
        ;
    }


    @Bean
    public AccessDeniedHandler  accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso denegado: No tienes los permisos suficientes");
        };
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
            .username("user")
            .password("{noop}user123")
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password("{noop}admin123")
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}

