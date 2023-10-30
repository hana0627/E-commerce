package msa.hana.userservice.api.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserLogin(
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email not be less than 2 characters")
        String username,
        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be equals or grater then 8 characters")
        String password
) {
}
