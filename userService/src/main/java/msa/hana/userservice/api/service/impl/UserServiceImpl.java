package msa.hana.userservice.api.service.impl;

import msa.hana.userservice.api.domain.UserAccount;
import msa.hana.userservice.api.dto.request.UserCreate;
import msa.hana.userservice.api.repository.UserRepository;
import msa.hana.userservice.api.service.UserService;
import msa.hana.userservice.global.config.security.CustomPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder customPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = customPasswordEncoder;
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public Long createUser(UserCreate requestDto) {
        UserAccount userAccount = UserAccount.builder()
                .name(requestDto.name())
                .userId(UUID.randomUUID().toString())
                .email(requestDto.email())
                .encryptedPassword(passwordEncoder.encode(requestDto.password()))
                .build();
        UserAccount savedUser = userRepository.save(userAccount);
        return savedUser.getId();

    }
}
