package in.ratansgh.models.user;

import in.ratansgh.models.enums.UserRole;
import in.ratansgh.models.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String id, String name) {
        super(id, name, UserRole.CUSTOMER);
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
