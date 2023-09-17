package exercise.controller;

import java.util.Collections;
import java.util.Objects;

//import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void root(Context ctx) {
        String name = ctx.sessionAttribute("currentUser");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue(name, Object.class);
//        var page = new MainPage(objectMapper);
        var page = new MainPage(name);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
    public static void buildSession(Context ctx) {
        ctx.render("build.jte");
    }

    public static void login(Context ctx) {
        String name = ctx.formParam("name");
        String enteredPassword = encrypt(ctx.formParam("password"));

        if (UsersRepository.existsByName(name)) {
            User user = UsersRepository.findByName(name);
            String usersPassword = user.getPassword();
            int hashcode1 = Objects.hashCode(enteredPassword);
            int hashcode2 = Objects.hashCode(usersPassword);
            if (hashcode1 == hashcode2 && name.equals(user.getName())) {
                ctx.sessionAttribute("currentUser", name);
                ctx.redirect(NamedRoutes.rootPath());
            } else {
                rebuildLogin(name, ctx);
            }
        }
        else {
            rebuildLogin(name, ctx);
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void rebuildLogin(String name, Context ctx) {
        LoginPage page = new LoginPage(name, "Wrong username or password");
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }
    // END
}
