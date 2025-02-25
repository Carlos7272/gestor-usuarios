package com.organization.user_manager.infraestructure.services;

import com.organization.user_manager.api.model.request.PostRequest;
import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.PostResponse;
import com.organization.user_manager.api.model.response.UserResponse;
import com.organization.user_manager.domain.entities.PostEntity;
import com.organization.user_manager.domain.entities.UserEntity;
import com.organization.user_manager.domain.repositories.PostRepository;
import com.organization.user_manager.domain.repositories.UserRepository;
import com.organization.user_manager.infraestructure.abstrac_services.IPostService;
import com.organization.user_manager.infraestructure.converter.post.PostConverter;
import com.organization.user_manager.util.enums.Tables;
import com.organization.user_manager.util.exceptions.CreateException;
import com.organization.user_manager.util.exceptions.IdNotFoundException;
import com.organization.user_manager.util.exceptions.UpdateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostConverter converter;

    @Override
    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(converter::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse read(Long id) {
        return postRepository.findById(id)
                .map(converter::convertEntityToResponse)
                .orElseThrow(() -> new IdNotFoundException(Tables.post.name()));
    }

    @Override
    public PostResponse create(PostRequest request) {
        var user = userRepository.findByFullName(request.getUserName())
                   .orElseThrow(() -> new IdNotFoundException(Tables.user.name()));

        PostEntity entity = converter.convertRequestToEntity(request);
        entity.setUser(user);
        try {
            entity = postRepository.save(entity);
        }catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new CreateException("post");
        }
        return (converter.convertEntityToResponse(entity));
    }

    @Override
    public PostResponse update(PostRequest request, Long id) {
        var postToUpdate = postRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.post.name()));
        var user = userRepository.findByFullName(request.getUserName())
                .orElseThrow(() -> new IdNotFoundException(Tables.user.name()));
        PostEntity postEntity = converter.convertRequestToEntity(request);
        postEntity.setId(postToUpdate.getId());
        postEntity.setUser(user);
        try {
            postEntity = postRepository.save(postEntity);
        }catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new UpdateException("post");
        }
        log.info("Post updated with id {}", postEntity.getId());
        return (converter.convertEntityToResponse(postEntity));
    }

    @Override
    public void delete(Long id) {
        var postToDelete = postRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.post.name()));
        postRepository.delete(postToDelete);
    }
}
