package msa.hana.userservice.api.service.impl;

import lombok.RequiredArgsConstructor;
import msa.hana.userservice.api.domain.UserAccount;
import msa.hana.userservice.api.dto.request.UserCreate;
import msa.hana.userservice.api.repository.UserRepository;
import msa.hana.userservice.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
                .encryptedPassword("encrypted_password")
                .build();
        UserAccount savedUser = userRepository.save(userAccount);
        return savedUser.getId();

    }
}
