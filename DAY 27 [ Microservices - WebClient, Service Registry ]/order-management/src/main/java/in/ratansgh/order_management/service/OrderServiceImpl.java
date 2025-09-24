package in.ratansgh.order_management.service;

import in.ratansgh.order_management.entities.Order;
import org.springframework.http.ResponseEntity;

public interface OrderServiceImpl {
    public ResponseEntity<Order> addDb (Long id, Integer quantity);
}
