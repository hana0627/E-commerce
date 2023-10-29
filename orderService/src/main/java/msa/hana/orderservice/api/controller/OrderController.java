package msa.hana.orderservice.api.controller;

import msa.hana.orderservice.api.dto.OrderCreate.OrderCreate;
import msa.hana.orderservice.api.dto.response.OrderResponse;
import msa.hana.orderservice.api.dto.response.OrderResponseData;
import msa.hana.orderservice.api.service.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/order-service")
@RestController
public class OrderController {

    private final Environment env;
    private final OrderService orderService;

    public OrderController(Environment env, OrderService orderService) {
        this.env = env;
        this.orderService = orderService;
    }

    @PostMapping("/{user_id}/orders")
    public ResponseEntity<OrderResponseData> createOrder(@RequestBody OrderCreate orderCreate, @PathVariable("user_id")String userId ) {
        Long orderId = orderService.createOrder(orderCreate, userId);
        OrderResponseData responseData = OrderResponseData.of("등록되었습니다.", orderId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseData);
    }
    @GetMapping("/{user_id}/orders")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable("user_id")String userId ) {
        List<OrderResponse> result = orderService.getOrdersByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{order_id}/order")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("order_id")String orderId ) {
        OrderResponse result = orderService.findOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }


}
