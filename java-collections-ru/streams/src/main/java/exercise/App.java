package exercise;

import java.util.List;
//import java.util.Arrays;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> list) {
        return (int) list.stream()
                .filter(email -> email.endsWith("yandex.ru") || email.endsWith("gmail.com") || email.endsWith("hotmail.com"))
                .count();
    }
}

// END
