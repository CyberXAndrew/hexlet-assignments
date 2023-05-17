package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        ObjectMapper objMap = new ObjectMapper();
        String values = objMap.writeValueAsString(this);
        return values;
    }
    public static Car unserialize(String value) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        Car result = objMap.readValue(value, Car.class);
        return result;
    }
    // END
}
