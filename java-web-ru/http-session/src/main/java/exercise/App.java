package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParam("page");
            var per = ctx.queryParam("per");
            if (Objects.equals(page, null) && Objects.equals(per, null)) {
                ctx.json(USERS.subList(0, 5));
            } else if ((Objects.equals(page, null) || Objects.equals((Integer.parseInt(page)), 1)) &&
                    !Objects.equals(per, null)) {
                ctx.json(USERS.subList(0, Integer.parseInt(per)));
            } else {
                assert per != null;
                int from = ((Integer.parseInt(per) * (Integer.parseInt(page) - 1)));
                int until = (Integer.parseInt(per) * (Integer.parseInt(page)));
                ctx.json(USERS.subList(from, until));
            }
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
