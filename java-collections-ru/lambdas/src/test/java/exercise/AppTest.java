package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    public void appMainTest() {
        String[][] originalArray = {
                {"*", "+", "*"},
                {"*", " ", "*"},
                {"*", "+", "*"},
        };
        String[][] expectedArray = {
                {"*", "*", "+", "+", "*", "*"},
                {"*", "*", "+", "+", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", "+", "+", "*", "*"},
                {"*", "*", "+", "+", "*", "*"},
        };
        assertThat(expectedArray).isEqualTo(App.enlargeArrayImage(originalArray));
    }
    @Test
    public void appEmptynessTest() {
        String[][] empty = {{}, {}};
        assertThat(empty).isEqualTo(App.enlargeArrayImage(new String[][] {{}}));
    }

    @Test
    public void appNullTest() {
        String[][] nullArray = {{null, null}, {null, null}};
        assertThat(nullArray).isEqualTo(App.enlargeArrayImage(new String[][] {{null}}));
    }
}

// END
