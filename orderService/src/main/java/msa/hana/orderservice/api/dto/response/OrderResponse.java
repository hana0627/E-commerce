package msa.hana.orderservice.api.dto.response;


import msa.hana.orderservice.api.domain.Order;

import java.time.LocalDateTime;

public record OrderResponse(
        String orderId,
        String productId,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        LocalDateTime createdAt

) {

    public static OrderResponse of(Order order) {
        return new OrderResponse(order.getOrderId(), order.getProductId(), order.getQuantity(), order.getUnitPrice(), order.getTotalPrice(), order.getCreatedAt());
    }

    public OrderResponse(String orderId, String productId, Integer quantity, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
