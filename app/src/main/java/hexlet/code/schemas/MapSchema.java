package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private int size = -1;

    public boolean isValid(Map<String, String> map) throws Exception {
        if (super.isValid(map)) {
            return isInSize();
        }
        return false;
    }

    public void sizeOf(int newSize) {
        size = newSize;
    }

    public boolean isInSize() {
        if (size == -1) {
            return true;
        }
        return map.size() == size;
    }
}
