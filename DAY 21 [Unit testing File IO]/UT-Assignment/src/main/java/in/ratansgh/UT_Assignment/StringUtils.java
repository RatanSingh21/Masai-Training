package in.ratansgh.UT_Assignment;

public class StringUtils {
    public boolean isPalindrome(String input) {
        if (input == null) return false;
        String clean = input.replaceAll("\\s+", "").toLowerCase();
        return new StringBuilder(clean).reverse().toString().equals(clean);
    }

    public String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    public boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}

