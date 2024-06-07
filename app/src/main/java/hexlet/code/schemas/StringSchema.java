package hexlet.code.schemas;

public class StringSchema {

    private boolean isRequiredSet;
    private boolean isContainsSet;
    private boolean isMinLengthSet;
    private String subString;
    private int minLength;

    public StringSchema() {
        isRequiredSet = false;
        isContainsSet = false;
        isMinLengthSet = false;
    }

    public boolean isValid(String str) {
        boolean result = true;
        if (isRequiredSet) {
            result = !(str == null || str.isEmpty());
        }
        if (result && isContainsSet) {
            result = str.contains(subString);
        }
        if (result && isMinLengthSet) {
            result = str.length() > minLength;
        }
        return result;
    }

    public void required() {
        isRequiredSet = true;
    }

    public StringSchema contains(String newSubString) {
        isContainsSet = true;
        subString = newSubString;
        return this;
    }

    public StringSchema minLength(int newMinLength) {
        isMinLengthSet = true;
        minLength = newMinLength;
        return this;
    }

}
