package msa.hana.userservice.global.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(CustomPasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[CustomAuthenticationProvider] authentication : {}", authentication);
        log.info("[CustomAuthenticationProvider] authentication.getName() : {}", authentication.getName());
        log.info("[CustomAuthenticationProvider] authentication.getCredentials : {}", authentication.getCredentials().toString());
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        CustomUserDetails u = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password,u.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of());
        }
        throw new BadCredentialsException("Something was wrong");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
