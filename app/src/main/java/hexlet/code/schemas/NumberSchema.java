package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    boolean isPositiveSet = false;
    boolean isRangeSet = false;
    private int[] range;

    public boolean isValid(int number) throws Exception {
        if (super.isValid(number)) {
            return isPositive() && isInRange();
        }
        return false;
    }

    public NumberSchema positive() {
        isPositiveSet = true;
        return this;
    }

    public boolean isPositive() {
        if (!isPositiveSet) {
            return true;
        }
        return number > 0;
    }

    public void range(int start, int end) {
        range = new int[2];
        range[0] = start;
        range[1] = end;
        isRangeSet = true;
    }

    public boolean isInRange() {
        if (!isRangeSet) {
            return true;
        }
        return number >= range[0] && number <= range[1];
    }
}
