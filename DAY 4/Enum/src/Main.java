public class Main {
    public static void main(String[] args) {

        // Day Concept
//        Day today = Day.Friday;
//        switch(today){
//            case Monday:
//                System.out.println("Today is Monday....");
//                break;
//
//            case Friday:
//                System.out.println("Today is Friday.....");
//                break;
//
//            default:
//                System.out.println("Having a bad day");
//        }

        // Order class examples
//        Order Or = new Order(1, OrderStatus.pending);
//        Or.printStatus();


        // Role based ENUM
//        Role aukat = Role.ADMIN;
//        switch (aukat){
//            case ADMIN:
//                System.out.println("ADMIN HU MAI");
//                break;
//
//            case USER:
//                System.out.println("Mai majdoor hu");
//                break;
//
//            case GUEST:
//                System.out.println("Aukat kai bahar...");
//                break;
//
//            default:
//                System.out.println("please login again...");
//        }
//

        // Statua Code
         StatusCode stat = StatusCode.SUCCESS;
        System.out.println("Status:" +stat);
        System.out.println("Code: " +stat.getCode());

    }
}
