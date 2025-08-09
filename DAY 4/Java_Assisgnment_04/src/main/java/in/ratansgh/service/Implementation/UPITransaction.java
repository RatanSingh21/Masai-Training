package in.ratansgh.service.Implementation;

import in.ratansgh.service.Interface.Logger;
import in.ratansgh.service.Interface.TransactionProcessor;

public class UPITransaction implements TransactionProcessor, Logger {

    private double balance = 1000;

    @Override
    public void process(double amount) {
        if (TransactionProcessor.validate(amount) && balance >= amount) {
            balance =balance- amount;
            System.out.println("UPI: Paid " + amount);
        } else {
            System.out.println("UPI: Failed to pay " + amount);
        }
    }

    @Override
    public boolean refund(double amount) {
        if (TransactionProcessor.validate(amount)) {
            balance =balance + amount;
            System.out.println("UPI: Refunded " + amount);
            return true;
        }
        logInfo("UPI: Refund failed :" +amount);
        return false;
    }

}
