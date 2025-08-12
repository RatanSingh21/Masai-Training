package in.ratansgh.services.strategy;

import in.ratansgh.models.order.Order;

public class NoDiscount implements DiscountStrategy {
    @Override public double applyDiscount(Order order) { return 0; }
}
