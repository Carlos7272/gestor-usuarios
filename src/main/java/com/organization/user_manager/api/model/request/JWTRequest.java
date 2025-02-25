package com.organization.user_manager.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JWTRequest implements Serializable {

    @NotBlank(message = "Name is mandatory")
    private String fullName;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
