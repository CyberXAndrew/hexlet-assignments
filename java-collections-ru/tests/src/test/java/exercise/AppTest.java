
package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static exercise.App.take;
class AppTest {
    @Test
    void testTake() {
        // BEGIN
        List<Integer> num = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThat(take(num, 0)).isEqualTo(new ArrayList<>(0));
        assertThat(take(num, 1)).isEqualTo(new ArrayList<>(Arrays.asList(1)));
        assertThat(take(num, 3)).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertThat(take(num, -1)).isEqualTo(new ArrayList<>(0));
        assertThat(take(num, 8)).isEqualTo(num);
        // END
    }
}

//    List<Integer> num = new ArrayList <>(Arrays.asList(1, 2, 3, 4, 5));
//        if (!take(num, 0).equals(new ArrayList <>(0))) {
//        throw new AssertionError("error 0");
//        } else if (!take(num, 1).equals(num.get(0))) {
//        throw new AssertionError("error 1");
//        } else if (!take(num, 3).equals(new ArrayList <>(Arrays.asList(1, 2, 3)))) {
//        throw new AssertionError("error 3");
//        } else if (!take(num, -1).equals(new ArrayList <>(0))) {
//        throw new AssertionError("error -1");
//        } else if (!take(num, 8).equals(num)) {
//        throw new AssertionError("error 8");
//        }