package Ex3;

public class Main {

    public static void main(String[] args) {

        // Start with String
        Pipeline<String, String> pipeline = new Pipeline<>();

        // String -> String
        Pipeline<String, String> step1 =
                pipeline.add(input -> input.trim());

        // String -> Integer
        Pipeline<String, Integer> step2 =
                step1.add(input -> input.length());

        // Integer -> Double
        Pipeline<String, Double> step3 =
                step2.add(input -> input * 2.5);

        // Execute the pipeline
        Double result = step3.execute("   Hello   ");

        System.out.println("Result: " + result);
    }
}