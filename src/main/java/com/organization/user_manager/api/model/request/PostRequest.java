package com.organization.user_manager.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRequest {

    @NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Title is required")
    private String title;
    private String content;
}
