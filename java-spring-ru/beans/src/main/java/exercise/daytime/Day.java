package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @Scope("prototype")
    @PostConstruct
    public String notifyCreation() {
        return "Bean was created";
    }
    // END
}
