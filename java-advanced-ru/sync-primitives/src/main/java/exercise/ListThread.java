package exercise;

// BEGIN
public class ListThread extends Thread {
    private final SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(i);
//            System.out.println(list.get(i));
        }
    }
}
// END
