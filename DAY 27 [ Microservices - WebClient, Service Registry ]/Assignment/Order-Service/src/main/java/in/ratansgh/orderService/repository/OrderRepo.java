package in.ratansgh.orderService.repository;

import in.ratansgh.orderService.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
