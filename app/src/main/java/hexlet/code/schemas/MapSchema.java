package hexlet.code.schemas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    private int size;

    public MapSchema() {
        super.addPredicate("isMap", isMap());
    }

    public void sizeOf(int newSize) {
        size = newSize;
        super.addPredicate("isInSize", isInSize());
    }

    public Predicate<Object> isInSize() {
        ObjectMapper mapper = new ObjectMapper();
        return p -> (mapper.convertValue(p, new TypeReference<Map<String, String>>() { }).size() == size);
    }

    public MapSchema required() {
        super.isRequiredSet = true;
        return this;
    }

    public Predicate<Object> isMap() {
        return p -> (p instanceof Map);
    }
}
