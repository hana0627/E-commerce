package msa.hana.userservice.api.dto.response;

public record UserResponse(
        String message,
        Long id
) {


    public static UserResponse of(String message, Long id) {
        return new UserResponse(message,id);
    }

    public UserResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }
}
