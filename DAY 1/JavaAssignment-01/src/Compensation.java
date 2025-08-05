public class Compensation {
    public static void main(String[] args) {
        float monthlyCTC = 58000.50F;

        // Typecasting
        int MonthlyCTCIntgers = (int)monthlyCTC;

        // Printing both the values
        System.out.println("The float value of monthlyCTC: " + monthlyCTC);
        System.out.println("The type casted value is: " + MonthlyCTCIntgers);

        // Annual CTC
        float annualCTC = monthlyCTC * 12;
        // Adding bonus
        annualCTC +=10000;
        System.out.println("Final Annual CTC after bonus is "+ annualCTC);
    }
}
