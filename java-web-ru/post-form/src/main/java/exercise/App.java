package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import java.util.Objects;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import io.javalin.http.HttpResponseException;
import io.javalin.http.RedirectResponse;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> ctx.render("users/build.jte"));

        app.post("/users", ctx -> {
            String firstName = StringUtils.capitalize(Objects.requireNonNull(ctx.formParam("firstName")).trim());
            String lastName = StringUtils.capitalize(Objects.requireNonNull(ctx.formParam("lastName")).trim());
            String email = Objects.requireNonNull(ctx.formParam("email")).trim().toLowerCase();
            String password = Security.encrypt(Objects.requireNonNull(ctx.formParam("password")).toLowerCase());
//            String passwordConfirmation = Security.encrypt((Objects.requireNonNull(
//                    ctx.formParam("passwordConfirmation")).toLowerCase()));
//            if (Objects.equals(password, passwordConfirmation)) {
                User newUser = new User(firstName, lastName, email, password);
                UserRepository.save(newUser);
                ctx.redirect("/users");
//            } else {
//                ctx.redirect("/users/build");
//            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
