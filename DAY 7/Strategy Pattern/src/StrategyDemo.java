import java.util.Arrays;


// Interface for Strategy e.g. Sorting
interface SortStrategy {
    void sort(int[] arr);
}


//  bubble sort logic
class BubbleSort implements SortStrategy {
    public void sort(int[] arr) {
        // simple bubble (inefficient) for demo
        for(int i=0;i<arr.length;i++){
            for(int j=1;j<arr.length-i;j++){
                if(arr[j-1] > arr[j]) {
                    int t = arr[j-1]; arr[j-1] = arr[j]; arr[j] = t;
                }
            }
        }
    }
}

// Quicksort logic
class QuickSort implements SortStrategy {
    public void sort(int[] arr) {
        Arrays.sort(arr); // delegate to JDK sort for demo // fake logic
    }
}

// main context logic
class SortContext {

    // interface object
    private SortStrategy strategy;

    // Constructor for the same
    public SortContext(SortStrategy strategy) {
        this.strategy = strategy;
    }

    // Setter for the object
    public void setStrategy(SortStrategy s) {
        this.strategy = s;
    }

    // method to pass the array to sort
    public void sort(int[] arr) {
        strategy.sort(arr);
    }
}

// Main class
public class StrategyDemo {

    public static void main(String[] args) {

        // array
        int[] data = {5,2,9,1};

        // object of the buublesort class
        SortContext ctx = new SortContext(new BubbleSort());

        // pass the array
        ctx.sort(data);

        System.out.println(Arrays.toString(data));

        // sorting via quicksort
        ctx.setStrategy(new QuickSort());

        int[] d2 = {7,3,8,4};
        ctx.sort(d2);

        System.out.println(Arrays.toString(d2));
    }
}
