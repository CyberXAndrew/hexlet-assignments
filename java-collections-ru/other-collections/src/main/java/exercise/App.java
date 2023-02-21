package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        Set<String> setOfAllKeys = new TreeSet<>(map1.keySet());
        setOfAllKeys.addAll(map2.keySet());

        for (String key: setOfAllKeys) {
            if (!map1.containsKey(key)) {
                resultMap.put(key, "added");
            } else if (!map2.containsKey(key)) {
                resultMap.put(key, "deleted");
            } else if (!map1.get(key).equals(map2.get(key))) {
                resultMap.put(key, "changed");
            } else {
                resultMap.put(key, "unchanged");
            }
        }
        return resultMap;
    }
}
//END
