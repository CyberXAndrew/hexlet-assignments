package exercise;

import java.util.ArrayList;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        char[] symbolsArray = symbols.toLowerCase().toCharArray();
        char[] wordArray = word.toLowerCase().toCharArray();
        ArrayList<String> list = new ArrayList<>(0);
        for (int i = 0; i < symbolsArray.length; i++) {
            list.add(String.valueOf(symbolsArray[i]));
        }
        if (symbolsArray.length < wordArray.length) {
            return false;
        }
        for (int k = 0; k < wordArray.length; k++) {
            if (!list.contains(String.valueOf(wordArray[k]))) {
                return false;
            } else {
                list.remove(String.valueOf(wordArray[k]));
            }
        }
        return true;
    }
}
//END
