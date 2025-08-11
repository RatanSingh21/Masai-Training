// Convert one interface to another.
// Lets classes work together that couldn't otherwise because of incompatible interfaces.
// Useful for legacy code integration.

// Target
interface PaymentGateway {
    boolean pay(String fromAccount, String toAccount, double amount);
}

// Adaptee (legacy)
class LegacyPaymentProcessor {
    // older signature:
    public String makePayment(String srcAcct, String destAcct, double amt) {
        // returns transaction id or null if fail
        System.out.println("Legacy processing...");
        return "TXN123";
    }
}

// Adapter
class LegacyPaymentAdapter implements PaymentGateway {

    // object of legacy code
    private final LegacyPaymentProcessor legacy;

    //constructor
    public LegacyPaymentAdapter(LegacyPaymentProcessor legacy) {
        this.legacy = legacy;
    }

    // override the interface
    @Override
    public boolean pay(String fromAccount, String toAccount, double amount) {
        String txn = legacy.makePayment(fromAccount, toAccount, amount);
        return txn != null;
    }
}

// Demo
public class AdapterPattern {
    public static void main(String[] args) {

        // object for the both the class
        LegacyPaymentProcessor legacy = new LegacyPaymentProcessor();
        PaymentGateway gateway = new LegacyPaymentAdapter(legacy);

        System.out.println("Payment success: " + gateway.pay("A", "B", 100.0));
    }
}
