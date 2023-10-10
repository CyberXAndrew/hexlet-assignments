package exercise;

import java.util.List;
import java.util.Optional;

import jakarta.websocket.server.PathParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        int begin = limit * (page - 1);
        int end = Math.min(begin + limit, posts.size());
        return posts.subList(begin, end);
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        return posts.stream().filter(soughtPost -> soughtPost.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> existedPost = posts.stream().filter(soughtPost -> soughtPost.getId().equals(id)).findFirst();
        if (existedPost.isPresent()) {
            Post returnablePost = existedPost.get();
            returnablePost.setId(post.getId());
            returnablePost.setTitle(post.getTitle());
            returnablePost.setBody(post.getBody());
        }
        return post;
    }

    @DeleteMapping("posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }
    // END
}
