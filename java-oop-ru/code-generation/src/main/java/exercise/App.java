package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws JsonProcessingException, IOException {
        Path absPath = path.toAbsolutePath().normalize();
        String values = car.serialize();
        Files.writeString(absPath, values); //,StandardOpenOption.WRITE);
    }
    public static Car extract(Path path) throws IOException {
        String values = Files.readString(path);
        Car car = Car.unserialize(values);
        return car;
    }
}
// END
