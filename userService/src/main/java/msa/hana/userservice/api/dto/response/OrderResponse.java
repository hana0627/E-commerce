package msa.hana.userservice.api.dto.response;

import java.time.LocalDateTime;

public record OrderResponse(
        String productId,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        LocalDateTime createdAt,
        String orderId
) {
}
