public class Address {

    private String city;
    private String state;
    private int pin;

    // Getter & Setter
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    // Constructor

    public Address(String city, String state, int pin) {

        System.out.println("This is parameterized constructor for address with three parameters");
        this.city = city;
        this.state = state;
        this.pin = pin;
    }

    // ToString to print the info like all the parameters in same line.
    // @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pin=" + pin +
                '}';
    }
}
