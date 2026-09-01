package Ex1;
public class Main {

    public static void main(String[] args) {

       
        String[] names = {"Ahmed", "Faisal", "Mohammed", "Ali"};

      
        PrintableList<String> printableList = new PrintableList<>(names);

       
        printableList.printItems();
    }
}