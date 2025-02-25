package com.organization.user_manager.api.model.request;

import com.organization.user_manager.api.model.response.RoleResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest implements Serializable {

    @Size(min = 1, max = 50, message = "The name can be up to 50 characters long")
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    @Size(min = 1, max = 50, message = "The email can be up to 50 characters long")
    @Email(message = "Invalid email")
    private String email;
    @Size(min = 1, max = 50, message = "The password can be up to 50 characters long")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotEmpty(message = "Roles are mandatory")
    private List<RoleRequest> roles;
}