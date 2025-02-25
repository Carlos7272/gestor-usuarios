package com.organization.user_manager.api.controller;

import com.organization.user_manager.api.model.request.PostRequest;
import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.PostResponse;
import com.organization.user_manager.api.model.response.UserResponse;
import com.organization.user_manager.infraestructure.abstrac_services.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "post")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {

    private final IPostService service;

    @Operation(summary = "Returns all posts")
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPost() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Returns a post by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<PostResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @Operation(summary = "Save in system a post")
    @PostMapping
    public ResponseEntity<PostResponse> post(@Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Operation(summary = "Update a user by id")
    @PutMapping(path = "{id}")
    public ResponseEntity<PostResponse> put(@Valid @RequestBody PostRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @Operation(summary = "Delete a post by id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
