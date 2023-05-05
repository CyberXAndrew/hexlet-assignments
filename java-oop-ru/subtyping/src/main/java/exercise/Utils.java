// Методы класса в этом файл нужны для самостоятельной работы

package exercise;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class Utils {
    public static String serialize(Map<String, String> map) { //превращает словарь в строку данных
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    public static Map<String, String> unserialize(String json) { //превращает строку в словарь
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        try {
            data = mapper.readValue(json, Map.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static String readFile(String path) { //Дает содержимое файла в виде строки по его строковому пути
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public static void writeFile(String path, String content) { //пишет строку в файл по пути
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.writeString(fullPath, content, StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
