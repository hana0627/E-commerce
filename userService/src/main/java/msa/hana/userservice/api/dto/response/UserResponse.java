package msa.hana.userservice.api.dto.response;

import msa.hana.userservice.api.domain.UserAccount;

import java.util.List;

public record UserResponse(
        String email,
        String name,
        String userId,
        List<OrderResponse> orders
) {

    public static UserResponse of(UserAccount user) {
        return new UserResponse(user.getEmail(), user.getName(), user.getUserId(), List.of());
    }

    public UserResponse(String email, String name, String userId, List<OrderResponse> orders) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.orders = orders;
    }
}
