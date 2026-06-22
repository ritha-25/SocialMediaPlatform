package org.atm.socialmedia.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuthorResponse {
    private String message;
    private String fullName;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
