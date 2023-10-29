package msa.hana.orderservice.api.dto.response;

public record OrderResponseData(
        String message,
        Long id
) {


    public static OrderResponseData of(String message, Long id) {
        return new OrderResponseData(message,id);
    }

    public OrderResponseData(String message, Long id) {
        this.message = message;
        this.id = id;
    }
}
