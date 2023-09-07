package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.util.Generator;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        try {
            Post post = PostRepository.find(id);
            PostPage page = new PostPage(post);
            ctx.render("posts/index.jte", Collections.singletonMap("page", page));
        } catch (Exception e) {
            throw new NotFoundResponse("Page not found");
        }
    }

    public static void showAll(Context ctx) {
        List<Post> usersList = PostRepository.getEntities();
        var pageNumber = ctx.queryParam("page");
        var page = new PostsPage(usersList, pageNumber);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
