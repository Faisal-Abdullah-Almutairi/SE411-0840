package Ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Pipeline<T, R> {

    private final List<Function<Object, Object>> transformers;

    // Constructor
    public Pipeline() {
        transformers = new ArrayList<>();
    }

    // Private constructor used when creating a new pipeline
    private Pipeline(List<Function<Object, Object>> transformers) {
        this.transformers = transformers;
    }

    // Add a transformer and return a new Pipeline with the new output type
    @SuppressWarnings("unchecked")
    public <N> Pipeline<T, N> add(Transformer<R, N> transformer) {

        List<Function<Object, Object>> newTransformers =
                new ArrayList<>(transformers);

        newTransformers.add(input ->
                transformer.transform((R) input)
        );

        return new Pipeline<>(newTransformers);
    }

    // Execute the pipeline
    @SuppressWarnings("unchecked")
    public R execute(T input) {

        Object result = input;

        for (Function<Object, Object> transformer : transformers) {
            result = transformer.apply(result);
        }

        return (R) result;
    }
}
