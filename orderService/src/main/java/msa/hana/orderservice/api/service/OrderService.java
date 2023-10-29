package msa.hana.orderservice.api.service;

import msa.hana.orderservice.api.dto.OrderCreate.OrderCreate;
import msa.hana.orderservice.api.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    Long createOrder(OrderCreate orderCreate, String userId);
    List<OrderResponse> getOrdersByUserId(String userId);
    OrderResponse findOrder(String orderId);
}
