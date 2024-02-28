package exercise;

import java.util.Arrays;

public class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int index = 0;

    public synchronized void add(int number) {
        if (index == array.length) {
            array = Arrays.copyOf(array, array.length + 1);
        }
        array[index] = number;
        System.out.println(array[index]);
        index++;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return array.length;
    }
    // END
}
