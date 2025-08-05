public class MultiplicationTable {
    public static void main(String[] args) {
        int[][] multiplicationTable = new int[3][3];

        // Store multiplication results
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                multiplicationTable[i][j] = (i + 1) * (j + 1);
            }
        }

        // Display the multiplication table
        System.out.println("--- 3x3 Multiplication Table ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4d", multiplicationTable[i][j]);
            }
            System.out.println(); // New line after each row
        }
    }
}