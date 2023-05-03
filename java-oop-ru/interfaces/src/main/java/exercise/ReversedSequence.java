// BEGIN
package exercise;

public class ReversedSequence implements CharSequence {
    private String input;
    ReversedSequence(String input) {
        this.input = reverse(input);
    }
    public static String reverse(String sentence) {
        StringBuilder stringbuilder = new StringBuilder(sentence);
        return stringbuilder.reverse().toString();
    }

    @Override
    public int length() {
        return input.length();
    }

    @Override
    public char charAt(int index) {
        return input.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return input.substring(start, end);
    }
}
// END
