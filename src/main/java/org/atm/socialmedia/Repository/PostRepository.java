package org.atm.socialmedia.Repository;

import org.atm.socialmedia.Model.Author;
import org.atm.socialmedia.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedBy(Author author);
    List<Post> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    void deleteByCreatedBy(Author author);
}
