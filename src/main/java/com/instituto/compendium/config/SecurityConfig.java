package com.instituto.compendium.config;

import com.instituto.compendium.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> 
                authorize
                    .requestMatchers(
                        "/",
                        "/registro",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/styles.css",
                        "/static/**",
                        "/webjars/**",
                        "/h2-console/**",
                        "/lista",
                        "/guias",
                        "/uploads/**",
                        "/error",
                        "/index"
                    ).permitAll()
                    .requestMatchers("/admin/**", "/juegos/**", "/roles/**", "/usuarios/**").hasRole("ADMIN")
                    .requestMatchers("/guias/nueva", "/guias/*/editar", "/guias/*/eliminar").hasAnyRole("ADMIN", "AUTOR")
                    .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .addHeaderWriter((request, response) -> {
                    if (request.getRequestURI().startsWith("/h2-console")) {
                        response.addHeader("X-Frame-Options", "SAMEORIGIN");
                    }
                }));

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }
}