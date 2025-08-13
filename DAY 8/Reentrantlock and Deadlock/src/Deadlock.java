
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    // Two locks that will cause the deadlock
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    // First method: gets lock1 first, then lock2
    public static void method1() {
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + " got lock1");
        try {

            // Wait a bit to make deadlock more likely
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + " trying to get lock2...");
            lock2.lock(); // This will cause deadlock!

            try {
                System.out.println(Thread.currentThread().getName() + " got both locks!");
            } finally {
                lock2.unlock();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock1.unlock();
        }

    }

    // Second method: gets lock2 first, then lock1 (OPPOSITE ORDER!)
    public static void method2() {
        lock2.lock();
        System.out.println(Thread.currentThread().getName() + " got lock2");

        try {

            // Wait a bit to make deadlock more likely
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + " trying to get lock1...");
            lock1.lock(); // This will cause deadlock!

            try {
                System.out.println(Thread.currentThread().getName() + " got both locks!");
            } finally {
                lock1.unlock();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock2.unlock();
        }

    }

}
