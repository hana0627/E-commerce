package msa.hana.orderservice.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.hana.orderservice.api.domain.Order;
import msa.hana.orderservice.api.dto.OrderCreate.OrderCreate;
import msa.hana.orderservice.api.dto.response.OrderResponse;
import msa.hana.orderservice.api.repository.OrderRepository;
import msa.hana.orderservice.api.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(OrderCreate requestDto, String userId) {

        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(requestDto.productId())
                .userId(userId)
                .quantity(requestDto.quantity())
                .unitPrice(requestDto.unitPrice())
                .totalPrice(requestDto.unitPrice() * requestDto.quantity())
                .createdAt(LocalDateTime.now())
                .build();

        Order savedEntity = orderRepository.save(order);

        return savedEntity.getId();

    }

    @Override
    public List<OrderResponse> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderResponse::of)
                .toList();
    }

    @Override
    public OrderResponse findOrder(String orderId) {
        return OrderResponse.of(orderRepository.findByOrderId(orderId)
                .orElseThrow(EntityNotFoundException::new));

    }
}
