package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private String subString;
    private int minLength;

    public StringSchema() {
        super.addPredicate("isString", isString());
    }

    public StringSchema contains(String newSubString) {
        subString = newSubString;
        super.addPredicate("isContain", isContain());
        return this;
    }

    public Predicate<Object> isContain() {
        return p -> (p.toString().contains(subString));
    }

    public StringSchema minLength(int newMinLength) {
        minLength = newMinLength;
        super.addPredicate("isInMinLength", isInMinLength());
        return this;
    }

    public Predicate<Object> isInMinLength() {
        return p -> (p.toString().length() > minLength);
    }

    public StringSchema required() {
        super.isRequiredSet = true;
        super.addPredicate("isInMinLength", isInMinLength());
        return this;
    }

    public Predicate<Object> isString() {
        return p -> (p instanceof String);
    }

}
