package msa.hana.userservice.api.dto.response;

public record UserResponseData(
        String message,
        Long id
) {


    public static UserResponseData of(String message, Long id) {
        return new UserResponseData(message,id);
    }

    public UserResponseData(String message, Long id) {
        this.message = message;
        this.id = id;
    }
}
