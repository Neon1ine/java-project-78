package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    public Map<String, Predicate<Object>> predicateMap = new HashMap<>();
    public boolean isRequiredSet = false;

    public boolean isValid(Object obj) {
        if (obj == null) {
            return !isRequiredSet;
        } else {
            for (Map.Entry<String, Predicate<Object>> predicate : predicateMap.entrySet()) {
                if (!predicate.getValue().test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addPredicate(String name, Predicate<Object> predicate) {
        predicateMap.put(name, predicate);
    }
}
