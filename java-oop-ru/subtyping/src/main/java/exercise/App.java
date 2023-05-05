package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.util.HashMap;
// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage db) {
        db.toMap().entrySet().stream()
                .forEach(pair -> {db.unset(pair.getKey()); db.set(pair.getValue(), pair.getKey());});
    }
}
// END
