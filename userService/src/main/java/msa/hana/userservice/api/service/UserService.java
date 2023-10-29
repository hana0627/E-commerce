package msa.hana.userservice.api.service;

import msa.hana.userservice.api.domain.UserAccount;
import msa.hana.userservice.api.dto.request.UserCreate;
import msa.hana.userservice.api.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    Long createUser(UserCreate requestDto);

    UserResponse findUser(String userId);

    List<UserResponse> getUsers();
}
