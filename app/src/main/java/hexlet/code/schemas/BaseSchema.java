package hexlet.code.schemas;

public class BaseSchema {

    public boolean isRequiredSet;
    public String line;
    public int number;

    public BaseSchema() {
        isRequiredSet = false;
    }

    public boolean isValid(String str) {
        line = str;
        boolean result = true;
        if (isRequiredSet) {
            result = !(str == null || str.isEmpty());
        }
        return result;
    }

    public boolean isValid(int newNumber) {
        this.number = newNumber;
        return true;
    }

    public void required() {
        isRequiredSet = true;
    }
}
