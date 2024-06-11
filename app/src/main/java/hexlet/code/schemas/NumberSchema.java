package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    private int[] range;

    public NumberSchema() {
        super.addPredicate("isNumber", isNumber());
    }

    public void range(int start, int end) {
        range = new int[2];
        range[0] = start;
        range[1] = end;
        super.addPredicate("isInMinRange", isInMinRange());
        super.addPredicate("isInMaxRange", isInMaxRange());
    }

    public Predicate<Object> isInMinRange() {
        return p -> (Integer.parseInt(p.toString()) >= range[0]);
    }

    public Predicate<Object> isInMaxRange() {
        return p -> (Integer.parseInt(p.toString()) <= range[1]);
    }

    public NumberSchema positive() {
        super.addPredicate("isPositive", isPositive());
        return this;
    }

    public Predicate<Object> isPositive() {
        return p -> (Integer.parseInt(p.toString()) > 0);
    }

    public NumberSchema required() {
        super.isRequiredSet = true;
        return this;
    }

    public Predicate<Object> isNumber() {
        return p -> (p instanceof Integer);
    }
}
