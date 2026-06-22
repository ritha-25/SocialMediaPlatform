package org.atm.socialmedia.Repository;

import org.atm.socialmedia.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUsername(String username);
    Optional<Author> findByEmail(String email);
    List<Author> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByUsername(String username);
}
