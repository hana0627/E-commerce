package msa.hana.orderservice.api.dto.OrderCreate;


import msa.hana.orderservice.api.domain.Order;

import java.time.LocalDateTime;

public record OrderCreate(
        String orderId,
        String productId,
        String userId,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        LocalDateTime createdAt

) {

}
