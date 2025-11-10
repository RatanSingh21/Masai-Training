package in.ratansgh.order_management.controller;

import in.ratansgh.order_management.entities.Order;
import in.ratansgh.order_management.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/order/{id}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long id , @RequestBody Integer quantity) {
        return orderService.addDb(id,quantity);
    }

}
