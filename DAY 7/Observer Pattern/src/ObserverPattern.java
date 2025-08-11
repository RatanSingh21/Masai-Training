// GOF Design pattern

// In observer design pattern we have three participants observer, Subject and concrete implementation
// Mainly used for Event driven systems like notifications and all LIKE SOFTWARE UPDATES AND ALL

import java.util.*;

// Observer which receives the notification
interface Observer {
    void update(String event);
}

// which register and unregister the observer and notify them
interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers(String event);
}

//
class EventManager implements Subject {

    // created the observers via list
    private final List<Observer> observers = new ArrayList<>();

    // REGISTER(ADD) observer in sync bcoz we want it to support multi threading
    @Override
    public synchronized void register(Observer o) {
        observers.add(o);
    }

    // UNREGISTER(REMOVE) observer in sync bcoz we want it to support multi threading
    @Override
    public synchronized void unregister(Observer o) {
        observers.remove(o);
    }

    // Notify observer
    @Override
    public void notifyObservers(String event) {

        // copy to avoid ConcurrentModification on self-unregister
        List<Observer> copy;
        synchronized(this) { copy = new ArrayList<>(observers); }
        for (Observer o : copy) {
            o.update(event);
        }
    }
}

// Observers:

// NOTIFICATION VIA EMAIL
class EmailNotifier implements Observer {

    // CREATED AN FIELD FOR EMAIL
    private final String email;

    // CONSTRUCTOR
    public EmailNotifier(String email) {
        this.email = email;
    }

    // UPDATE METHOD
    public void update(String event) {
        System.out.println("Email to " + email + ": " + event);
    }
}

// NOTIFICATION VIA SMS
class SmsNotifier implements Observer {

    // CREATED AN FIELD FOR SMS i.e phone number
    private final String phone;

    // CONSTRUCTOR
    public SmsNotifier(String phone) {
        this.phone = phone;
    }

    // update method
    public void update(String event) {
        System.out.println("SMS to " + phone + ": " + event);
    }
}

// MAIN CLASS
public class ObserverPattern {

    public static void main(String[] args) {

        // one observer created
        EventManager subject = new EventManager();
        System.out.println("..........One observer created.......");
        subject.register(new EmailNotifier("user1@hdfc.com"));
        subject.register(new SmsNotifier("9876543210"));

        subject.notifyObservers("New Policy created: POL001");


        // Second observer created
        EventManager subject2 = new EventManager();
        System.out.println("..........SECOND observer created.......");
        subject2.register(new EmailNotifier("user2@hdfc.com"));
        subject2.register(new SmsNotifier("12345678"));

        subject2.notifyObservers("New Policy created: POL002");

    }
}
