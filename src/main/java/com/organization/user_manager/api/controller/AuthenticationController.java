package com.organization.user_manager.api.controller;

import com.organization.user_manager.api.model.request.JWTRequest;
import com.organization.user_manager.api.model.response.JWTResponse;
import com.organization.user_manager.infraestructure.services.security.JWTService;
import com.organization.user_manager.infraestructure.services.security.JWTUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "authenticate")
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUserDetailService jwtUserDetailService;
    private final JWTService jwtService;

    @Operation(summary = "User authentication")
    @PostMapping
    public ResponseEntity<JWTResponse> postToken(@RequestBody JWTRequest request) {
        this.authenticate(request);
        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getFullName());
        final var token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getFullName(), request.getPassword()));
        } catch (BadCredentialsException | DisabledException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}