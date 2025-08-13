import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ForkJoin Sum Example ===");

        // Create ForkJoinPool (the manager)
        ForkJoinPool pool = new ForkJoinPool();

        // Create the main task
        SumTask task = new SumTask(1, 100000);

        // Record start time
        long startTime = System.currentTimeMillis();

        // Execute the task and get result
        Long result = pool.invoke(task);

        // Record end time
        Long endTime = System.currentTimeMillis();

        // Show results
        System.out.println("\n=== RESULTS ===");
        System.out.println("Sum from 1 to 100000 = " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        // Compare with sequential calculation
        System.out.println("\n=== Comparison with Sequential ===");

        startTime = System.currentTimeMillis();
        long sequentialSum = 0;

        for (int i = 1; i <= 100000; i++) {
            sequentialSum += i;
        }

        endTime = System.currentTimeMillis();

        System.out.println("Sequential sum = " + sequentialSum);
        System.out.println("Sequential time: " + (endTime - startTime) + " ms");

        // Shutdown the pool
        pool.shutdown();

    }
}
