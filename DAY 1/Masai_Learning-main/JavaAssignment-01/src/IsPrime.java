public class IsPrime {
    public static void main(String[] args) {
        for (int i = 2; i < 51; i++) {

            boolean IsPrime = true;

            for( int j = 2; j < i; j++){
                if(i % j == 0){
                    IsPrime = false;
                    break;
                }
            }

            if(IsPrime){
                System.out.println(i);
            }
        }
    }
}
