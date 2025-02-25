package com.organization.user_manager.infraestructure.services;

import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.UserResponse;
import com.organization.user_manager.domain.entities.UserEntity;
import com.organization.user_manager.domain.repositories.UserRepository;
import com.organization.user_manager.infraestructure.abstrac_services.IUserService;
import com.organization.user_manager.infraestructure.converter.user.UserConverter;
import com.organization.user_manager.util.enums.Tables;
import com.organization.user_manager.util.exceptions.CreateException;
import com.organization.user_manager.util.exceptions.IdNotFoundException;
import com.organization.user_manager.util.exceptions.UpdateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter converter;

    @Override
    public List<UserResponse> getAll() {
        return this.repository.findAll().stream()
                .map(converter::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse create(UserRequest request) {
        UserEntity entity = converter.convertRequestToEntity(request);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity userSave;
        try {
            userSave = repository.save(entity);
        }catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new CreateException("user");
        }
        return (converter.convertEntityToResponse(userSave));
    }

    @Override
    public UserResponse read(Long id) {
        return repository.findById(id)
                .map(converter::convertEntityToResponse)
                .orElseThrow(() -> new IdNotFoundException(Tables.user.name()));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        var userToUpdate = repository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.user.name()));
        UserEntity userEntity = converter.convertRequestToEntity(request);
        userEntity.setId(userToUpdate.getId());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity userUpdated;
        try {
            userUpdated = repository.save(userEntity);
        }catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new UpdateException("user");
        }
        log.info("User updated with id {}", userUpdated.getId());
        return (converter.convertEntityToResponse(userUpdated));
    }

    @Override
    public void delete(Long id) {
        var userToDelete = repository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.user.name()));
        repository.delete(userToDelete);
        log.info("User deleted with id {}", userToDelete.getId());
    }
}