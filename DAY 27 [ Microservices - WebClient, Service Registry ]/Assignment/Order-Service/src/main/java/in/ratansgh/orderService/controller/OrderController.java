package in.ratansgh.orderService.controller;

import in.ratansgh.orderService.dto.OrderResponse;
import in.ratansgh.orderService.entities.Order;
import in.ratansgh.orderService.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderWithPaymentStatus(
            @PathVariable Long id,
            @RequestParam(defaultValue = "rest") String method) {
        return orderService.getOrderWithPaymentStatus(id, method);
    }
}
