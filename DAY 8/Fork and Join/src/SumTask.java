import java.util.concurrent.RecursiveTask;

//Step 1: Create a task that extends RecursiveTask
public class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000; // When to stop splitting
    private final int start;
    private final int end;


    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {

        // If task is small enough, do it directly
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            System.out.println(Thread.currentThread().getName() +
                    " calculated sum from " + start + " to " + end + " = " + sum);
            return sum;

        }

        // If task is too big, split it into two parts
        int middle = start + (end - start) / 2;

        // Create two smaller tasks
        SumTask leftTask = new SumTask(start, middle);
        SumTask rightTask = new SumTask(middle + 1, end);

        // Fork: Start the left task in parallel
        leftTask.fork();

        // Compute the right task in current thread
        long rightResult = rightTask.compute();

        // Join: Wait for left task to complete and get its result
        long leftResult = leftTask.join();

        // Combine results
        long totalSum = leftResult + rightResult;

        System.out.println("Combined results: " + leftResult + " + " + rightResult + " = " + totalSum);
        return totalSum;

    }
}