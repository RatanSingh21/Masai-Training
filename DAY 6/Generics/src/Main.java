// Generic Class
class Box<T> {

    private T value;

    public void setValue(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Main {

    public static void main(String[] args) {

        // Generic

        // Box class of string type
        Box<String> stringbox =  new Box<>();
        stringbox.setValue("Hello");
        System.out.println(
                "The valuve setted via Generic  String Class is: " + stringbox.getValue()
        );

        // Box class of integer type
        Box<Integer> Integerbox =  new Box<>();
        Integerbox.setValue(1234);
        System.out.println(
                "The valuve setted via Generic Integer Class is: " + Integerbox.getValue()
        );

        // Wild Cardss


    }
}