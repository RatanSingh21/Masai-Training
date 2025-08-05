import java.util.Scanner;

public class TrainingScores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of training scores (n): ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Number of scores must be positive.");
            return;
        }

        int[] scores = new int[n];
        int sum = 0;
        int highestScore = Integer.MIN_VALUE;
        int lowestScore = Integer.MAX_VALUE;

        System.out.println("Enter " + n + " training scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
            sum += scores[i];
            if (scores[i] > highestScore) {
                highestScore = scores[i];
            }
            if (scores[i] < lowestScore) {
                lowestScore = scores[i];
            }
        }

        double average = (double) sum / n;

        System.out.println("\n--- Score Analysis ---");
        System.out.println("Average score: " + String.format("%.2f", average));
        System.out.println("Highest score: " + highestScore);
        System.out.println("Lowest score: " + lowestScore);
    }
}