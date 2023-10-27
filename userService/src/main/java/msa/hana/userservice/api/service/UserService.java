package msa.hana.userservice.api.service;

import msa.hana.userservice.api.dto.request.UserCreate;

public interface UserService {
    public Long createUser(UserCreate requestDto);
}
