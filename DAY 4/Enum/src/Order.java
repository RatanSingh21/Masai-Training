public class Order {

    // Fields defined
    int orderId;
    OrderStatus status;

    // Constructor
    public Order(int id, OrderStatus status) {
        this.orderId= id;
        this.status = status;
    }

    // Helper functions
    public void printStatus(){
        System.out.println("Order ID: " + orderId + " is " + status);
    }
}
