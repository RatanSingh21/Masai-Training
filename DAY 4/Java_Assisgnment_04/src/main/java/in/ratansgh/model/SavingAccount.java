package in.ratansgh.model;

public class SavingAccount extends Account{

    public SavingAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public double calculateMonthlyInterest() {
        return balance*0.04/12;
    }

    public void applyCashback(String type){
        System.out.println("Cashback applied for :" +type);
    }

    public void applyCashback(String type, double percent){
        double cashback =balance*(percent/100);
        balance =balance+cashback;
        System.out.println("Cashback of :"+cashback+"Type"+type);


    }
}
