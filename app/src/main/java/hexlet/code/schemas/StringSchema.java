package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    private String subString;
    private int minLength = -1;

    public boolean isValid(String str) throws Exception {
        if (super.isValid(str)) {
            return isContains() && isInMinLength();
        }
        return false;
    }

    public StringSchema contains(String newSubString) {
        subString = newSubString;
        return this;
    }

    public boolean isContains() {
        if (subString == null) {
            return true;
        }
        return line.contains(subString);
    }

    public StringSchema minLength(int newMinLength) {
        minLength = newMinLength;
        return this;
    }

    public boolean isInMinLength() {
        if (minLength == -1) {
            return true;
        }
        return line.length() > minLength;
    }

}
