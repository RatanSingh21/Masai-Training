// Reentrant is an Alternative for Synchronized to give more flexible alternatives

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Reentrant Example
        Reentrant example = new Reentrant();
        // Create 3 threads that will increment the counter
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    example.increment();
                    try {
                        Thread.sleep(100); // Small delay

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    }
                }, "Thread-" + i);
                threads[i].start();
            }
        // Wait for all threads to finish

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final counter value: " + example.getCounter());


        // Deadlock

        System.out.println("Starting deadlock example...");
        System.out.println("This program will get stuck (deadlock)!");

        Thread thread1 = new Thread(() -> Deadlock.method1(), "Thread-1");
        Thread thread2 = new Thread(() -> Deadlock.method2(), "Thread-2");

        thread1.start();
        thread2.start();

        // Wait 3 seconds to see the deadlock
        Thread.sleep(3000);
        System.out.println("DEADLOCK DETECTED! Program is stuck.");
        System.out.println("You'll need to stop the program manually.");

        // In a real program, you'd handle this better
        System.exit(0);


    }

}
