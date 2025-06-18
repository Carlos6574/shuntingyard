import java.util.*;
public class Constant {
    private final Map<String, String> constants;

    public Constant() {
        this.constants = new HashMap<>();
        this.constants.put("pi", "3.14");
        this.constants.put("e", "2.71828");
        this.constants.put("nice", "69");
        this.constants.put("lucky", "7");
    }

    public String getConstant(String c) {
        return this.constants.get(c);
    }
}
