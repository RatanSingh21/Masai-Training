package in.ratansgh.orderService.dto;

import in.ratansgh.orderService.entities.Order;
import lombok.Data;

@Data
public class OrderResponse {

    private Order order;
    private String paymentStatus;
}
