package Ex4;

import java.util.Arrays;
import java.util.List;

public class Main {

    // Accepts a list of any type and prints each item
    public static void printList(List<?> list) {

        for (Object item : list) {
            System.out.println(item);
        }
    }

    // Accepts a list of any type that extends Number
    public static double sumNumbers(List<? extends Number> numbers) {

        double sum = 0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum;
    }

    public static void main(String[] args) {

        // Test printList with Strings
        List<String> names = Arrays.asList(
                "Ahmed",
                "Faisal",
                "Mohammed"
        );

        System.out.println("Names:");
        printList(names);

        // Test printList with Integers
        List<Integer> numbers = Arrays.asList(
                10,
                20,
                30
        );

        System.out.println("\nNumbers:");
        printList(numbers);

        // Test sumNumbers with Integers
        System.out.println("\nInteger sum:");
        System.out.println(sumNumbers(numbers));

        // Test sumNumbers with Doubles
        List<Double> decimals = Arrays.asList(
                1.5,
                2.5,
                3.5
        );

        System.out.println("\nDouble sum:");
        System.out.println(sumNumbers(decimals));
    }
}
