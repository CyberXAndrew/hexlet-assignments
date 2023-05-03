package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> realty, int n) {
        List<String> result = new ArrayList<>();
        return realty.stream()
                .sorted(Comparator.comparing(x -> x.getArea()))
                .limit(n)
                .map(x -> x.toString())
                .collect(Collectors.toCollection(() -> result));
    }
}
// END
