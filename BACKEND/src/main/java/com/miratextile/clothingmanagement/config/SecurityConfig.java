package com.miratextile.clothingmanagement.config;

import com.miratextile.clothingmanagement.security.JwtAuthenticationFilter;
import com.miratextile.clothingmanagement.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/users/**").hasAnyAuthority("STORE_MANAGER")
                .requestMatchers("/api/skus/**").hasAnyAuthority("STORE_MANAGER", "INVENTORY_STAFF")
                .requestMatchers("/api/inventory/**").hasAnyAuthority("STORE_MANAGER", "INVENTORY_STAFF")
                .requestMatchers("/api/customers/**").hasAnyAuthority("STORE_MANAGER", "SALES_STAFF")
                .requestMatchers("/api/orders/**").hasAnyAuthority("STORE_MANAGER", "SALES_STAFF")
                .requestMatchers("/api/payments/**").hasAnyAuthority("STORE_MANAGER", "SALES_STAFF")
                .requestMatchers("/api/returns/**").hasAnyAuthority("STORE_MANAGER", "SALES_STAFF")
                .requestMatchers("/api/credits/**").hasAnyAuthority("STORE_MANAGER")
                .requestMatchers("/api/reports/**").hasAnyAuthority("STORE_MANAGER")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}