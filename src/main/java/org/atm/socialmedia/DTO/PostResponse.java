package org.atm.socialmedia.DTO;

import lombok.Data;
import org.atm.socialmedia.Model.Visibility;
import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String message;
    private String title;
    private String content;
    private Visibility visibility;
    private LocalDateTime createdAt;
    private String createdBy;
}
