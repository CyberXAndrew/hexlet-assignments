package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;

public class Night implements Daytime {
    private String name = "night";

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
