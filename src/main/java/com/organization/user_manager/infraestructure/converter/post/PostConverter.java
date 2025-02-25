package com.organization.user_manager.infraestructure.converter.post;

import com.organization.user_manager.api.model.request.PostRequest;
import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.PostResponse;
import com.organization.user_manager.domain.entities.PostEntity;
import com.organization.user_manager.domain.entities.RoleEntity;
import com.organization.user_manager.domain.entities.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {

    public PostResponse convertEntityToResponse(PostEntity source) {
        PostResponse response = new PostResponse();
        BeanUtils.copyProperties(source, response);
        return response;
    }

    public PostEntity convertRequestToEntity(PostRequest source) {
        PostEntity entity = new PostEntity();
        BeanUtils.copyProperties(source, entity);
        return entity;
    }




}
