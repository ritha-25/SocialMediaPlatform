package org.atm.socialmedia.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atm.socialmedia.DTO.AuthorResponse;
import org.atm.socialmedia.DTO.CreateAuthorRequest;
import org.atm.socialmedia.DTO.UpdateBioRequest;
import org.atm.socialmedia.Service.AuthorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AuthorResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authorService.getByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthorResponse> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(authorService.getByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/period")
    public ResponseEntity<List<AuthorResponse>> getByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(authorService.getAuthorsByPeriod(start, end));
    }

    @PatchMapping("/{id}/bio")
    public ResponseEntity<AuthorResponse> updateBio(@PathVariable Long id, @RequestBody UpdateBioRequest request) {
        return ResponseEntity.ok(authorService.updateBio(id, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody CreateAuthorRequest request) {
        return ResponseEntity.ok(authorService.updateProfile(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}
