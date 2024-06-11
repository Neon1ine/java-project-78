package hexlet.code.schemas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class BaseSchema {

    public boolean isRequiredSet;
    public String line;
    public int number;
    public Map<String, String> map;

    public BaseSchema() {
        isRequiredSet = false;
    }

    public boolean isValid(Object obj) throws Exception {
        if (obj == null) {
            return !isRequiredSet;
        } else {
            if (obj.getClass().getName().contains("String")) {
                this.line = obj.toString();
                if (isRequiredSet) {
                    return !(line.isEmpty());
                }
            } else if (obj.getClass().getName().contains("Number") || obj.getClass().getName().contains("Integer")) {
                this.number = Integer.parseInt(obj.toString());
            } else if (obj.getClass().getName().contains("Map")) {
                ObjectMapper mapper = new ObjectMapper();
                this.map = mapper.convertValue(obj, new TypeReference<Map<String, String>>() { });
            } else {
                throw new Exception("Wrong Schema format: " + obj.getClass().getName());
            }
        }
        return true;
    }

    public void required() {
        isRequiredSet = true;
    }
}
