package com.organization.user_manager.domain.repositories;

import com.organization.user_manager.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByFullName(String userName);
}