package com.organization.user_manager.infraestructure.converter.user;

import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.RoleResponse;
import com.organization.user_manager.api.model.response.UserResponse;
import com.organization.user_manager.domain.entities.RoleEntity;
import com.organization.user_manager.domain.entities.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserEntity convertRequestToEntity(UserRequest source) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(source, entity);
        var roles = source.getRoles().stream()
                .map(role -> {
                    var roleEntity = new RoleEntity();
                    BeanUtils.copyProperties(role, roleEntity);
                    return roleEntity;
                })
                .toList();
        entity.setRoles(roles);
        return entity;
    }

    public UserResponse convertEntityToResponse(UserEntity source) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(source, response);
        var roles = source.getRoles().stream()
                .map(role -> {
                    RoleResponse roleResponse = new RoleResponse();
                    BeanUtils.copyProperties(role, roleResponse);
                    return roleResponse;
                })
                .toList();
        response.setRoles(roles);
        return response;
    }
}