package in.ratansgh.services.strategy;

import in.ratansgh.models.order.Order;

public class PercentageDiscount implements DiscountStrategy {
    private double rate;

    public PercentageDiscount(double rate) { this.rate = rate; }

    @Override
    public double applyDiscount(Order order) {
        return order.getBaseAmount() * rate;
    }
}
