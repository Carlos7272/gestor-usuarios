package com.organization.user_manager.api.controller;

import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.UserResponse;
import com.organization.user_manager.infraestructure.abstrac_services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final IUserService service;

    @Operation(summary = "Returns all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Returns a user by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @Operation(summary = "Save in system a user")
    @PostMapping
    public ResponseEntity<UserResponse> post(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Operation(summary = "Update a user by id")
    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponse> put(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @Operation(summary = "Delete a user by id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}