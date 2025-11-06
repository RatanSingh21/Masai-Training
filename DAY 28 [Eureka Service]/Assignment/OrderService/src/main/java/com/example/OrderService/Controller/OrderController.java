package com.example.OrderService.Controller;


import com.example.OrderService.Dto.ResponseDto;
import com.example.OrderService.Entity.Order;
import com.example.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order)
    {
        return service.placeOrder(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ResponseDto> getOrderById(@PathVariable Long id){
        return service.getById(id);
    }

}
