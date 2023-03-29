package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(array -> arrayElementDoubler(array))
                .flatMap(array -> Arrays.stream(new String[][] {array, array}))
                .toArray(String[][]::new);
    }

    public static String[] arrayElementDoubler(String[] array) {
        return Arrays.stream(array)
                .flatMap(sign -> Arrays.stream(new String[] {sign, sign}))
                .toArray(String[]::new);
    }
}
// END
