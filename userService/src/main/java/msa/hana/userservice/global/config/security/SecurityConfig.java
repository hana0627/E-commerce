package msa.hana.userservice.global.config.security;

import msa.hana.userservice.global.config.security.filter.CustomAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomAuthenticationProvider authenticationProvider) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers("/users/**").authenticated()
//                        .antMatchers("/users/**").permitAll()
                )
                .formLogin(login -> login
//                        .loginPage("/users/login")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll() // 로그인 페이지는 누구나 접근 가능
                )
                .authenticationProvider(authenticationProvider)
                .build();

    }
}
