package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    public void testValidator() {
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
}
