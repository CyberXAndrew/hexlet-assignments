package exercise;

import io.javalin.Javalin;
//import io.javalin.http.Context;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/phones", ctx -> ctx.json(Data.getPhones()));
        app.get("/domains", ctx -> ctx.json(Data.getDomains()));

        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
