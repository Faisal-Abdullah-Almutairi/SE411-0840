package Ex1;
import java.util.Arrays;
import java.util.List;

public class PrintableList<T> {

    private List<T> list;

   
    public PrintableList(T[] items) {
        list = Arrays.asList(items);
    }

   
    public void printItems() {
        for (T item : list) {
            System.out.println(item);
        }
    }
}