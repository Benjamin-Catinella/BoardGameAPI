package com.quack.boardgameapi.security;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    private final MyUserDetailsService userDetailsService;
    private AuthenticationConfiguration authenticationConfiguration;

    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public SecurityConfig(final MyUserDetailsService userDetailsService, AuthenticationConfiguration authenticationConfiguration, JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtTokenAuthenticationFilter = jwtTokenAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        final var authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);
        // Activer CORS et désactiver CSRF
        http = http.cors().and().csrf().disable();
        // Modifier le manager de session pour utiliser le mode STATELESS
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        // Renvoyer un code d’erreur en cas d’accès non autorisé
        http = http.exceptionHandling().authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                ).and();

        // Définir les autorisations d’accès aux ressources
        http.authorizeHttpRequests()
        // Les accès sans autorisation
                .requestMatchers("/api/public/**").permitAll()
        // Les autres accès
                .anyRequest().authenticated();
        // Injecte notre filtre pour qu’il s’exécute avant le traitement du filtre UsernamePasswordAuthenticationFilter

        http.addFilterBefore(
                jwtTokenAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        try {
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
