package in.ratansgh;

import in.ratansgh.entity.BankAccount;
import in.ratansgh.service.BankAccountServiceImpl;

public class Main {
    public static void main(String[] args) {

//        System.out.println("Welcome to Exception handling :)");
//
//        int [] nums = {1,2,3,4,5,6};
//
//        division(2,2);
//        array(nums);


        BankAccount BankAccount_01 = new BankAccount(12 ,"Ratan Singh" ,987654 ,8760);
        BankAccountServiceImpl BankService = new BankAccountServiceImpl(BankAccount_01);
        BankService.checkBalance(BankAccount_01);
        BankService.depositAmount(BankAccount_01, 60);
        BankService.withdrawAmount(BankAccount_01, 8830);

    }

//    public static void division(int a, int b){
//
//        try{
//            int division = a/b;
//            System.out.println("The division of two number is: " + division);
//        } catch (ArithmeticException e) {
//            System.out.println("Something went wrong...");
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }

//    public static void array( int [] arr){
//        try {
//            System.out.println("The element at 6th position is: " + arr[6]);
//        } catch (ArrayIndexOutOfBoundsException e){
//            System.out.println("Array out of bound check size of array...");
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }
}