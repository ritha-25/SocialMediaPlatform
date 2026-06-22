package org.atm.socialmedia.Service;

import lombok.RequiredArgsConstructor;
import org.atm.socialmedia.DTO.CreatePostRequest;
import org.atm.socialmedia.DTO.PostResponse;
import org.atm.socialmedia.DTO.UpdatePostRequest;
import org.atm.socialmedia.Model.Author;
import org.atm.socialmedia.Model.Post;
import org.atm.socialmedia.Repository.AuthorRepository;
import org.atm.socialmedia.Repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostResponse createPost(CreatePostRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.getAuthorId()));
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedBy(author);
        postRepository.save(post);
        return toResponse(post, "Post created successfully");
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(p -> toResponse(p, ""))
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        return postRepository.findByCreatedBy(author)
                .stream()
                .map(p -> toResponse(p, ""))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return toResponse(post, "Post found");
    }

    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        if (request.getTitle() != null) post.setTitle(request.getTitle());
        if (request.getContent() != null) post.setContent(request.getContent());
        postRepository.save(post);
        return toResponse(post, "Post updated successfully");
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    @Transactional
    public void deletePostsByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        postRepository.deleteByCreatedBy(author);
    }

    @Transactional
    public void deletePostsByPeriod(LocalDateTime start, LocalDateTime end) {
        List<Post> posts = postRepository.findByCreatedAtBetween(start, end);
        postRepository.deleteAll(posts);
    }

    private PostResponse toResponse(Post post, String message) {
        PostResponse response = new PostResponse();
        response.setMessage(message);
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setVisibility(post.getVisibility());
        response.setCreatedAt(post.getCreatedAt());
        response.setCreatedBy(post.getCreatedBy().getFullName());
        return response;
    }
}
