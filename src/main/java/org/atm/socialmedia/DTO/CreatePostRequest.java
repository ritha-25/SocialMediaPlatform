package org.atm.socialmedia.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostRequest {

    @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters")
    private String title;

    @Size(min = 50, max = 2000, message = "Content must be between 50 and 2000 characters")
    private String content;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}
