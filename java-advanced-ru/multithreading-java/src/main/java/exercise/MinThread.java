package exercise;

// BEGIN
public class MinThread extends Thread {
    private final int[] array;
    private int result;

    public int getResult() {
        return result;
    }

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Integer minNumber = array[0];
        for (Integer number : array) {
            if (number < minNumber) {
                minNumber = number;
            }
        }
        this.result = minNumber;
    }
}
// END
