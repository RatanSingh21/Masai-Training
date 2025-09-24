package in.ratansgh.order_management.repository;

import in.ratansgh.order_management.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
