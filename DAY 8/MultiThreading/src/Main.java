public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World.....");

        System.out.println(
                "This program is running in " + Thread.currentThread().getName() + " Thread"
        );

        MyThread myThread = new MyThread();
        myThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println( i + " from " + Thread.currentThread().getName());
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}