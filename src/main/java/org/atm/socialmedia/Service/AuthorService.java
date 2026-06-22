package org.atm.socialmedia.Service;

import lombok.RequiredArgsConstructor;
import org.atm.socialmedia.DTO.AuthorResponse;
import org.atm.socialmedia.DTO.CreateAuthorRequest;
import org.atm.socialmedia.DTO.UpdateBioRequest;
import org.atm.socialmedia.Model.Author;
import org.atm.socialmedia.Repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        if (authorRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken: " + request.getUsername());
        }
        Author author = new Author();
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());
        authorRepository.save(author);
        return toResponse(author, "Author created successfully");
    }

    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        return toResponse(author, "Author found");
    }

    public AuthorResponse getByUsername(String username) {
        Author author = authorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Author not found with username: " + username));
        return toResponse(author, "Author found");
    }

    public AuthorResponse getByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found with email: " + email));
        return toResponse(author, "Author found");
    }

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(a -> toResponse(a, ""))
                .collect(Collectors.toList());
    }

    public List<AuthorResponse> getAuthorsByPeriod(LocalDateTime start, LocalDateTime end) {
        return authorRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(a -> toResponse(a, ""))
                .collect(Collectors.toList());
    }

    public AuthorResponse updateBio(Long id, UpdateBioRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        author.setBio(request.getBio());
        authorRepository.save(author);
        return toResponse(author, "Bio updated successfully");
    }

    public AuthorResponse updateProfile(Long id, CreateAuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        if (!author.getUsername().equals(request.getUsername()) && authorRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken: " + request.getUsername());
        }
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());
        authorRepository.save(author);
        return toResponse(author, "Profile updated successfully");
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    private AuthorResponse toResponse(Author author, String message) {
        AuthorResponse response = new AuthorResponse();
        response.setMessage(message);
        response.setFullName(author.getFullName());
        response.setUsername(author.getUsername());
        response.setEmail(author.getEmail());
        response.setCreatedAt(author.getCreatedAt());
        return response;
    }
}
