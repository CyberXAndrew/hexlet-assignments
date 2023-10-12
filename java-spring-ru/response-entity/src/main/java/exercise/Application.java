package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "1") Long limit) {
        List<Post> soughtList = posts.stream().limit(limit).toList();
        return ResponseEntity.ok().header("X-Total-Count",
                String.valueOf(posts.size())).body(soughtList);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Optional<Post> result = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        //of
        return result.map(post -> ResponseEntity.ok().body(post)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post data) {
        posts.add(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        Optional<Post> post = posts.stream()
                .filter(soughtPost -> soughtPost.getId().equals(id))
                .findFirst();
        if (post.isPresent()) {
            Post updatedPost = post.get();
            updatedPost.setId(data.getId());
            updatedPost.setTitle(data.getTitle());
            updatedPost.setBody(data.getBody());
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(204).body(data);
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
