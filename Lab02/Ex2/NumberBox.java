package Ex2;
import java.util.List;

public class NumberBox<T extends Number> {

    private T item;


    public void setItem(T item) {
        this.item = item;
    }

  
    public T getItem() {
        return item;
    }

    
    public double sum(List<? extends Number> numbers) {
        double total = 0;

        for (Number number : numbers) {
            total += number.doubleValue();
        }

        return total;
    }
}
