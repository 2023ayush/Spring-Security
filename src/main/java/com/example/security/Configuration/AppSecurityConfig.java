package com.example.security.Configuration;


import com.example.security.JwtFilter;
import com.example.security.Service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfig {
//    @Bean
//    public PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers(
//                                        "/api/v1/auth/register/",
//                                        "/v3/api-docs/**",
//                                        "/swagger-ui/**",
//                                        "/swagger-ui.html",
//                                        "/swagger-resources/**",
//                                        "/webjars/**").permitAll()
//                                .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//}
//
//    @Autowired
//    private CustomerUserDetailsService customerUserDetailsService;
//
//    String[] publicEndpoints = {
//            "/api/v1/auth/register",
//            "/api/v1/auth/login",
//            "/api/v1/auth/update-password",
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html",
//            "/swagger-resources/**",
//            "/webjars/**"
//    };
//
//    @Bean
//    public PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(customerUserDetailsService);
//        authProvider.setPasswordEncoder(getEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception{
//
//        http.authorizeHttpRequests( req -> {
//            req.requestMatchers("/api/v1/auth/register", "/api/v1/auth/login")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated();
//        });
//
//        return http.csrf().disable().build();
//    }


    //////////////////////////////For Role Based Access we should use this//////////////////////////////////////////

//    @Configuration
//    @EnableWebSecurity
//    public class AppSecurityConfig {
//
//        @Autowired
//        private CustomerUserDetailsService customerUserDetailsService;
//
//        String[] publicEndpoints = {
//                "/api/v1/auth/register",
//                "/api/v1/auth/login",
//                "/api/v1/auth/update-password",
//                "/v3/api-docs/**",
//                "/swagger-ui/**",
//                "/swagger-ui.html",
//                "/swagger-resources/**",
//                "/webjars/**"
//        };
//
//        @Bean
//        public PasswordEncoder getEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//        @Bean
//        public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//            return config.getAuthenticationManager();
//        }
//
//        @Bean
//        public AuthenticationProvider authProvider() {
//
//            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//            authProvider.setUserDetailsService(customerUserDetailsService);
//            authProvider.setPasswordEncoder(getEncoder());
//
//            return authProvider;
//        }
//
//        @Bean
//        public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
//
//            http.authorizeHttpRequests(req -> {
//                req.requestMatchers(publicEndpoints)
//                        .permitAll()
//                        .requestMatchers("/api/v1/admin/welcome").hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated();
//            }).httpBasic();
//
//            return http.csrf().disable().build();
//        }
//
//
//    }
//////////////////////////////////////////FOR JWT TOKEN///////////////////////////////////////////////////////////
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JwtFilter filter;

    String[] publicEndpoints = {
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/update-password",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customerUserDetailsService);
        authProvider.setPasswordEncoder(getEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests( req -> {
                    req.requestMatchers(publicEndpoints)
                            .permitAll()
                            .requestMatchers("/api/v1/admin/welcome").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                }) .authenticationProvider(authProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.csrf().disable().build();
    }


}
