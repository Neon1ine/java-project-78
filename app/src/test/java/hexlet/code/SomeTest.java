package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest {
    @Test
    public void firstTest() {
        assertThat(App.getGretingString()).isEqualTo("Hello world!");
    }
}
