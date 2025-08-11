// Attach additional responsibilities to an object dynamically

// interface for Coffee
interface Coffee {
    String getDescription();
    double cost();
}

// simple coffee class
class SimpleCoffee implements Coffee {

    public String getDescription() {
        return "Simple Coffee";
    }

    public double cost() {
        return 30;
    }
}

// created a decorator i.e added more field/ resposibility  to it
abstract class CoffeeDecorator implements Coffee {

    // created aa field
    protected final Coffee decoratedCoffee;

    // constructor for the same
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    // override interface
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    public double cost() {
        return decoratedCoffee.cost();
    }

}

// one more responsible to add milk to coffee
class MilkDecorator extends CoffeeDecorator {

    // constructor to make milk decorator
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    public double cost() {
        return decoratedCoffee.cost() + 10;
    }

}

// one more responsibility to add sugar to coffee
class SugarDecorator extends CoffeeDecorator {

    // constructor to make inheritance
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    // override methods
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    public double cost() {
        return decoratedCoffee.cost() + 5;
    }

}

// main
public class DecoratorPattern {

    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);

        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription() + " = ₹" + coffee.cost());
    }
}
