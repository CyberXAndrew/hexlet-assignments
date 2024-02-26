package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) {

    }

    public static Map<String, Integer> getMinMax(int[] array) {
        Map<String, Integer> result = new HashMap<>();

        LOGGER.info("Starting MaxThread");
        MaxThread maxThread = new MaxThread(array);
        LOGGER.info("Starting MinThread");
        MinThread minThread = new MinThread(array);
        maxThread.start();
        minThread.start();

        try {
            LOGGER.info("Waiting for MaxThread and MinThread to finish");
            maxThread.join();
            minThread.join();
            LOGGER.info("MaxThread and MinThread finished");
        } catch (InterruptedException ex) {
            System.out.println("Поток был прерван");
            LOGGER.info("Thread was interrupted " + ex.getMessage());
        }

        result.put("min", minThread.getResult());
        result.put("max", maxThread.getResult());
        LOGGER.info(result.toString());


        return result;
    }
    // END
}
