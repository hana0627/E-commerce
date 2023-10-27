package msa.hana.userservice.api.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserCreate(
    @NotNull(message = "Email Cannot be null")
    @Size(min = 2, message = "Email not be less then two characters")
    String email,
    @NotNull(message = "Name Cannot be null")
    @Size(min=2, message = "Email not be less then two characters")
    String name,
    @NotNull(message = "Password Cannot be null")
    @Size(min = 8, message = "Password must be equal or grater then 8 characters")
    String password

) {
}
