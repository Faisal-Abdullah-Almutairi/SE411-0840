package Ex2;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

       
        NumberBox<Integer> integerBox = new NumberBox<>();

        integerBox.setItem(10);

        System.out.println("Integer item: " + integerBox.getItem());

        List<Integer> integerNumbers = Arrays.asList(10, 20, 30, 40);

        System.out.println("Integer sum: " + integerBox.sum(integerNumbers));


       
        NumberBox<Double> doubleBox = new NumberBox<>();

        doubleBox.setItem(5.5);

        System.out.println("Double item: " + doubleBox.getItem());

        List<Double> doubleNumbers = Arrays.asList(1.5, 2.5, 3.5, 4.5);

        System.out.println("Double sum: " + doubleBox.sum(doubleNumbers));
    }
}