package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class DELETE {
    public static HashMap<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> dictionary = new HashMap<>();
        ArrayList<String> array = Arrays.asList(sentence.split(" "));
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            index = 1;
            String word = array.get(i);
            for (int k = 0; k < array.size(); k++) {
                if (array[i].equals(array[k] && i != k)) {
                    index += 1;
                    array.remove(array[k]);
                }
                numbers[i] = index;
            }
        }
        int i = 0;
        for (String key : dictionary.keySet()) {
            key = array[i];
            dictionary.put(key, numbers[i]);
            i += 1;
        }
        return dictionary;
    }

    public static String toString(Map dictionary) {
        String result = "{\n";
        for (String key : dictionary.keySet()) {
            result += key + ": " + dictionary.get(key) + "\n";
        }
        result += "\n}";
        return result;
    }
}

