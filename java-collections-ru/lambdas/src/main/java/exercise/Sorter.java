package exercise;

//import java.util.Comparator;
import java.util.Map;
import java.util.List;
//import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> list) {
        return list.stream()
                .filter(gender -> gender.get("gender").equals("male"))
                .sorted((element1, element2) -> element1.get("birthday").compareTo(element2.get("birthday")))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }
}
// END
