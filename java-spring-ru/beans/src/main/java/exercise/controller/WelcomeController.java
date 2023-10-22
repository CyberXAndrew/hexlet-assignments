package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Daytime currentDaytime;

    @GetMapping("/welcome")
    public String welcome() {
        String daytimeName = currentDaytime.getName();
        return "It is " + daytimeName + " now! Welcome to Spring!";
    }
}
// END
