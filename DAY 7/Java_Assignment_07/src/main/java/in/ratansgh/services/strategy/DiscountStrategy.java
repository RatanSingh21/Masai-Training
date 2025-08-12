package in.ratansgh.services.strategy;

import in.ratansgh.models.order.Order;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}
