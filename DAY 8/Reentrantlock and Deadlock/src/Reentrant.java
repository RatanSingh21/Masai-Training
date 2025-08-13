import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {

    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {

        lock.lock(); // Acquired the lock

        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented the counter to: " + counter);
        } finally {
            lock.unlock(); // Always unlock in finally block
        }

    }

    public int getCounter() {

        lock.lock();

        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }

}