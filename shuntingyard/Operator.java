import java.util.*;

public class Operator {
    private final Map<String, Double> op;

    public Operator() {
        op = new HashMap<>();
    }

    public double getAnswer(String token, double a, double b) {
        op.put("+", a + b);
        op.put("-", a - b);
        op.put("*", a * b);
        op.put("/", a / b);
        op.put("^", Math.pow(a, b));
        return op.get(token);
    }
}
