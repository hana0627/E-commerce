package msa.hana.userservice.global.config.security;

import lombok.RequiredArgsConstructor;
import msa.hana.userservice.api.domain.UserAccount;
import msa.hana.userservice.api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userService.findUserByEmail(email);

        return userAccount.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다!"));
    }
}
