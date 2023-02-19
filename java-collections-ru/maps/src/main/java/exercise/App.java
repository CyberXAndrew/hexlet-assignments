package exercise;

import java.util.*;


class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> dictionary = new HashMap<>();
        if (sentence.equals("")) {
            return dictionary;
        }

        String[] splittedSent = sentence.split(" ");
        List<String> array = new ArrayList<>(0);
        Collections.addAll(array, splittedSent);

        for (int i = 0; i < array.size(); i++) {
            Integer num = Collections.frequency(array, array.get(i)); //getWordEntries(array, array.get(i));
            dictionary.put(array.get(i), num);
        }
        return dictionary;
    }

    public static Integer getWordEntries(List<String> arrayList, String word) {
        int index = 0;
        for (int k = 0; k < arrayList.size(); k++) {
            if (arrayList.get(k).equals(word) && !arrayList.get(k).equals("")) {
                index += 1;
                arrayList.set(k, "");
            }
        }
        return index;
    }

    public static String toString(Map<String, Integer> dictionary) {
        var result = new StringBuilder("{");
        if (dictionary.isEmpty()) {
            return "{}";
        }
        for (Map.Entry<String, Integer> dict: dictionary.entrySet()) {
            result.append("\n  ");
            result.append(dict.getKey());
            result.append(": ");
            result.append(dict.getValue());
        }
        result.append("\n}");
        return result.toString();
    }
}
