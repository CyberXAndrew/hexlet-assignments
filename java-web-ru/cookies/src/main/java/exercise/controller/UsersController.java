package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import java.util.Objects;

import exercise.repository.UserRepository;

import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context ctx) throws Exception {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = Security.encrypt(ctx.formParam("password"));

        String token = Security.generateToken();
        ctx.cookie("token", token);

        ctx.cookie("token", Security.generateToken());
        String token = ctx.cookie("token");

        User newUser = new User(firstName, lastName, email, password, token);
        UserRepository.save(newUser);
        User createdUser = UserRepository.getEntities().stream()
                .filter(user -> user.getToken().equals(token))
                .findAny()
                .orElse(null);
        String id = String.valueOf(createdUser.getId());
        ctx.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context ctx) throws Exception {
        Long id = Long.parseLong(ctx.pathParam("id"));
        String token = ctx.cookie("token");
        User existingUser = UserRepository.getEntities().stream()
                .filter(user -> user.getToken().equals(token))
                .findAny()
                .orElse(null);
        if (id.equals(existingUser.getId())) {
            ctx.render("users/show.jte", Collections.singletonMap("user", existingUser));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
