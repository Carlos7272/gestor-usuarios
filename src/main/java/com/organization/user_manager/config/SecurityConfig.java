package com.organization.user_manager.config;

import com.organization.user_manager.infraestructure.component.CsrfCookieFilter;
import com.organization.user_manager.infraestructure.component.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception {
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth ->
                         auth
                             .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
                             .requestMatchers(HttpMethod.PUT, "/user/**").hasRole("USER")
                             .requestMatchers(HttpMethod.DELETE, "/user").hasRole("USER")
                             .requestMatchers(HttpMethod.GET, "/post/**").hasRole("USER")
                             .requestMatchers(HttpMethod.PUT, "/post/**").hasRole("USER")
                             .requestMatchers(HttpMethod.DELETE, "/post").hasRole("USER")
                         .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
        http.csrf(csrf -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers( "/user","/user/**","/post","/post/**", "/authenticate")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}