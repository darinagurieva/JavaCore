import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "One", "Two", "Three", "Four", "One",
                "Five", "Six", "Seven", "Seven", "Seven",
                "Eight", "Nine", "Ten", "Ten", "Eleven",
                "Twelve", "Nine", "One", "Eight", "Three");

        Set<String> unique = new HashSet<String>(words);

        System.out.println("Original array: ");
        System.out.println(words.toString());
        System.out.println("Unique words: ");
        System.out.println(unique.toString());
        System.out.println("Frequency: ");
        for (String key : unique) {
            System.out.println(key + ": " + Collections.frequency(words, key));
        }
    }
}