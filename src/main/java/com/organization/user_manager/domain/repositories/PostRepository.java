package com.organization.user_manager.domain.repositories;

import com.organization.user_manager.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
