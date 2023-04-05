package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String getForwardedVariables(String file) {
        String[] strings = file.split("\n");
        return Arrays.stream(strings)
                .filter(str -> str.startsWith("environment"))
                .map(str -> str.replaceAll("environment=\"", ""))
                .map(str -> str.replaceAll("\"", ""))
                .map(str -> str.split(","))
                .flatMap(x -> Stream.of(x))
                .filter(str -> str.startsWith("X_FORWARDED_"))
                .map(str -> str.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
