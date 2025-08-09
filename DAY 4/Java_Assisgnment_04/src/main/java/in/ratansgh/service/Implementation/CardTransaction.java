package in.ratansgh.service.Implementation;

import in.ratansgh.service.Interface.TransactionProcessor;

public class CardTransaction implements TransactionProcessor {

    private double balance = 3000;

    @Override
    public void process(double amount) {
        if (TransactionProcessor.validate(amount) && balance >= amount) {
            balance =balance- amount;
            System.out.println("Card: Paid " + amount);
        } else {
            System.out.println("Card: Failed to pay " + amount);
        }
    }

    @Override
    public boolean refund(double amount) {
        if (TransactionProcessor.validate(amount)) {
            balance =balance+ amount;
            System.out.println("Card: Refunded " + amount);
            return true;
        }
        return false;
    }


}
