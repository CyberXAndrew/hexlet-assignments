package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import exercise.model.Post;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitleAndBody(String title, String body);
}
