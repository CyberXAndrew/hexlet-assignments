package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> postsBase = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> show(@PathVariable String id) {
        List<Post> soughtList = postsBase.stream()
                .filter(post -> String.valueOf(post.getUserId()).equals(id))
                .toList();
        return soughtList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{id}/posts")
    public Post create(@PathVariable String id, @RequestBody Post data) {
        Post newPost = new Post();
        newPost.setUserId(Integer.valueOf(id));
        newPost.setSlug(data.getSlug());
        newPost.setTitle(data.getTitle());
        newPost.setBody(data.getBody());
        postsBase.add(newPost);
        return newPost;
    }
}
