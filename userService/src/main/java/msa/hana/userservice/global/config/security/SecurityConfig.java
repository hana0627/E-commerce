package msa.hana.userservice.global.config.security;

import lombok.RequiredArgsConstructor;
import msa.hana.userservice.api.repository.UserRepository;
import msa.hana.userservice.api.service.UserService;
//import msa.hana.userservice.global.config.security.filter.AuthenticationFilter;
import msa.hana.userservice.global.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {


    private final CustomAuthenticationProvider authenticationProvider;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final UserService userService;
    private final Environment env;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers("/health_check").permitAll()
                        .antMatchers("/welcome").permitAll()
                        .antMatchers("/users/**").permitAll()
                        .antMatchers("/actuator/**").permitAll()
                        .antMatchers("/**").authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .apply(new CustomFilter()) // 커스텀 필터 등록
                .and()
//                .addFilter(customAuthenticationFilter())
                .build();
    }


    public class CustomFilter extends AbstractHttpConfigurer<CustomFilter, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
//                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, env));
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager,userRepository));
        }
    }


//    @Bean
//    public AuthenticationFilter customAuthenticationFilter() throws Exception {
//            AuthenticationFilter filter = new AuthenticationFilter(userService,env);
//            filter.setAuthenticationManager(authenticationManager());
//            return filter;
//    }

}
