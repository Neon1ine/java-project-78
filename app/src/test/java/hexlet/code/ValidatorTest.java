package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    public void testStringValidator() {
        var v = new Validator();
        var schema = v.string();

        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid(null)).isEqualTo(false);

        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);

        assertThat(schema.contains("wh").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
        assertThat(schema.isValid("what does the fox say")).isEqualTo(false);

        var schema1 = v.string();
        assertThat(schema1.minLength(10).minLength(4).isValid("Hexlet")).isEqualTo(true);
    }

    @Test
    public void testNumberValidator() {
        var v = new Validator();
        var schema = v.number();
        assertThat(schema.isValid(5)).isEqualTo(true);

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.positive().isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(10)).isEqualTo(true);

        assertThat(schema.isValid(-10)).isEqualTo(false);
        assertThat(schema.isValid(0)).isEqualTo(false);

        schema.range(5, 10);
        assertThat(schema.isValid(5)).isEqualTo(true);
        assertThat(schema.isValid(10)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(false);
        assertThat(schema.isValid(11)).isEqualTo(false);
    }

    @Test
    public void testMapValidator() {
        var v = new Validator();
        var schema = v.map();
        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(new HashMap<>())).isEqualTo(true);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isEqualTo(true);

        schema.sizeOf(2);
        assertThat(schema.isValid(data)).isEqualTo(false);
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isEqualTo(true);
    }

    @Test
    public void testInnerMapValidator() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema.isValid(human1)).isEqualTo(true);
        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertThat(schema.isValid(human2)).isEqualTo(false);
        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertThat(schema.isValid(human3)).isEqualTo(false);
    }
}
