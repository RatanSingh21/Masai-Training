public class Palindrome {

    public static boolean isPalindrome(int x) {

        // Negative numbers are not palindrome
        // Numbers ending in 0 (but not 0 itself) are not palindrome
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }

        // For even number of digits: x == reversed
        // For odd number of digits: x == reversed / 10 (middle digit doesn't matter)
        return (x == reversed) || (x == reversed / 10);
    }

    public static void main(String[] args) {
        int[] testCases = {121, -121, 10, 12321, 0, 1, 1221};

        for (int x : testCases) {
            System.out.println("Is " + x + " a palindrome? " + isPalindrome(x));
        }
    }


}
