package exercise;

import java.util.Map;
import java.util.HashMap;
// BEGIN
public class FileKV implements KeyValueStorage {
    private String filepath;
    private Map<String, String> db = new HashMap<>();

    public FileKV(String filepath, Map<String, String> db) {
        this.filepath = filepath;
        this.db = new HashMap<>(db);
    }

    public void set(String key, String value) {
        String fileContent = Utils.readFile(filepath);
        Map<String, String> intermediateMap = Utils.unserialize(fileContent);
        intermediateMap.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(intermediateMap));
    }
    public void unset(String key) {
        String fileContent = Utils.readFile(filepath);
        Map<String, String> intermediateMap = Utils.unserialize(fileContent);
        intermediateMap.remove(key);
        Utils.writeFile(filepath, Utils.serialize(intermediateMap));
    }
    public String get(String key, String defaultValue) {
        String fileContent = Utils.readFile(filepath);
        Map<String, String> intermediateMap = Utils.unserialize(fileContent);
        return intermediateMap.getOrDefault(key, defaultValue);
    }
    public Map<String, String> toMap() {
        String fileContent = Utils.readFile(filepath);
        Map<String, String> map = Utils.unserialize(fileContent);
        return new HashMap<>(map);
    }
}
// END
