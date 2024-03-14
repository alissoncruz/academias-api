package com.core.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("!https")
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/validate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/signin").permitAll()

                        .requestMatchers(HttpMethod.POST, "/aluno").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aluno/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aluno").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aluno/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/aluno/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/aluno/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/despesa").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aula").permitAll()

                        .requestMatchers(HttpMethod.POST, "/mensalidade/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mensalidade").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensalidade").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensalidade/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensalidade/mensal/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensalidade/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/mensalidade/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/mensalidade/{id}").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/mensalidade/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/actuator").permitAll()
                        .requestMatchers(HttpMethod.POST, "/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        //.requestMatchers("/swagger-ui/**", "/v3/api-docs/", "/swagger-ui.html", "/webjars/*").permitAll()
                        //.requestMatchers("/v3/**", "/swagger-ui/**").permitAll()

                        .requestMatchers("/swagger-ui/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
