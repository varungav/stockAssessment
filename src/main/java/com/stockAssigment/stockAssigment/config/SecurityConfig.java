package com.stockAssigment.stockAssigment.config;

import com.stockAssigment.stockAssigment.Entity.UserEntity;
import com.stockAssigment.stockAssigment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/stocks/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserEntity userEntity = userRepository.findByUsername(username);
                if (userEntity == null) {
                    throw new UsernameNotFoundException("User not Found");
                }

                return createUserDetails(userEntity);
            }
        };
    }
    private UserDetails createUserDetails(UserEntity userEntity) {
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
