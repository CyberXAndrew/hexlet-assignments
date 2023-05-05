package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> dict = new HashMap<>();

    public InMemoryKV(Map<String, String> dict) {
        this.dict = new HashMap(dict);
    }

    public void set(String key, String value) {
        dict.put(key, value);
    }
    public void unset(String key) {
        dict.remove(key);
    }
    public String get(String key, String defaultValue) {
        return dict.getOrDefault(key, defaultValue);
    }
    public Map<String, String> toMap() {
        return new HashMap<>(dict);
    }
}
// END
