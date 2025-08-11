public class SingletonPattern {

    // Used in database connection -->> Ensure only one instance exists.

    private static SingletonPattern object;

    public SingletonPattern() {
    }

    public static SingletonPattern getInstance(){
        if(object == null){
            object = new SingletonPattern();
        }

        return object;
    }

}
