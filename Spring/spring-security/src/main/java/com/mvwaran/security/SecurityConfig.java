package com.mvwaran.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FromDatabaseUserDetailsService fromDatabaseUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/dashboard/manager").hasRole("manager");
                    registry.requestMatchers("/dashboard/hr").hasAnyRole("hr", "manager");
                    registry.requestMatchers("/dashboard/developer").hasAnyRole("developer", "manager");
                    registry.requestMatchers("/login").permitAll();
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login")
                        .usernameParameter("empId")
                        .successForwardUrl("/dashboard")
                        .permitAll())
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

//    /*
//    For in-memory based auth
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails guestUser = User.builder()
//                .username("guest")
//                .password("$2a$10$qR5cGWIkc.KNqH9gxW2VpuJSYbKvYUnJu9zK8lwNS2VV0de1/9JKi")
//                .roles("GUEST")
//                .build();
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$10$MeszZeMJ1f7jWOUFmnZgD.WHH55Vx4soiPh7yKP5ACxtY2urOgo2i")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(guestUser, adminUser);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(fromDatabaseUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
