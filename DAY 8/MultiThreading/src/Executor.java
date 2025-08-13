import java.util.concurrent.*;

public class Executor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);
//
//        executor.execute(
//                ()-> {
//                    for (int i = 0; i < 5; i++) {
//                        System.out.println( i + " from " + Thread.currentThread().getName());
//                        try{
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e){
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//        );


        Callable<String> task = () -> {
            Thread.sleep(5000);
            return  "Hello " + Thread.currentThread().getName();
        };

        System.out.println("Before calling the executor task....");
        Future<String> result = executor.submit(task);
        System.out.println("After calling the executor task.....");
        try{
            System.out.println("Waiting for the result......");
            System.out.println("Result from future object: " + result.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Something went wrong.....");
        }

        executor.shutdown();
    }
}
