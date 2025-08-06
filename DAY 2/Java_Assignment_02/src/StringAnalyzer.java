public class StringAnalyzer {
    public static void main(String[] args) {
        String str1 = "Hello World";
        String str2 = "hello world";
        String str3 = "Hello Java";

        // charAt()
        System.out.println("Character at index 4 in str1: " + str1.charAt(4)); // Output: o

        // substring()
        System.out.println("Substring of str1 from index 6: " + str1.substring(6)); // Output: World
        System.out.println("Substring of str1 from index 0 to 5: " + str1.substring(0, 5)); // Output: Hello

        // indexOf()
        System.out.println("Index of 'o' in str1: " + str1.indexOf('o')); // Output: 4
        System.out.println("Index of 'World' in str1: " + str1.indexOf("World")); // Output: 6

        // equals()
        System.out.println("str1 equals str2 (case-sensitive): " + str1.equals(str2)); // Output: false
        System.out.println("str1 equals str2 (case-insensitive): " + str1.equalsIgnoreCase(str2)); // Output: true

        // compareTo()
        System.out.println("Comparison of str1 and str2: " + str1.compareTo(str2)); // Output: A positive value (H comes after h in ASCII)
        System.out.println("Comparison of str1 and str3: " + str1.compareTo(str3)); // Output: A negative value (W comes before J)
    }
}