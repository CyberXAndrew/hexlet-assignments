package exercise;

// BEGIN
public class MaxThread extends Thread {
    private final int[] array;
    private int result;

    public int getResult() {
        return result;
    }

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Integer maxNumber = array[0];
        for (Integer number : array) {
            if (number > maxNumber) {
                maxNumber = number;
            }
        }
        this.result = maxNumber;
    }
}
// END
