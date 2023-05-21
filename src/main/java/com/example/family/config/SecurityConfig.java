package com.example.family.config;

import com.example.family.domain.user.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .requestMatchers("/residents").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/certificate/log").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/certificate/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf()
                .disable()
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(auth2AuthorizedClientService())
                .and()
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(User.withUsername("admin")
                .password("{noop}admin")
                .authorities("ROLE_ADMIN")
                .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("91a0b5900c5b05413e29")
                .clientSecret("b5cc29223ee89221448ef679869649b7a6ae3dba")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService auth2AuthorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }
}
