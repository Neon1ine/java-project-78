package hexlet.code.schemas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    private int size;
    private Map<String, BaseSchema> schemas;

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

    public void shape(Map<String, BaseSchema> newSchemas) {
        schemas = newSchemas;
        super.addPredicate("isShapeCorrect", isShapeCorrect());
    }

    private Predicate<Object> isShapeCorrect() {
        return p -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.convertValue(p, new TypeReference<>() { });
            for (Map.Entry<String, BaseSchema> oneSchema : schemas.entrySet()) {
                if (!oneSchema.getValue().isValid(map.get(oneSchema.getKey()))) {
                    return false;
                }
            }
            return true;
        };
    }
}
