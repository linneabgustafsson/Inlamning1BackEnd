package com.linnea.Inlamning1BackEnd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        //Helt publika endpoints
                        //.requestMatchers("/admin/members").permitAll()

                        //Borde väl bara vara admins här?
                        //Eller behöver medlemmen ha tillgång till databasen
                        //för att se myPages?
                        .requestMatchers("/h2-console/**").permitAll()

                         //Endpoints för admins
                        .requestMatchers("/admin/members/**").hasRole("ADMIN")

                        //Endpoints för medlemmar
                        //  /** betyder alltså allt
                        .requestMatchers("/mypages/members/**").hasAnyRole("ADMIN", "MEMBER")


                        //Om alla andra endpoints kräver inloggning
                        .anyRequest().authenticated()


                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
