package in.ratansgh.OrderServiceNew.repository;

import in.ratansgh.OrderServiceNew.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
