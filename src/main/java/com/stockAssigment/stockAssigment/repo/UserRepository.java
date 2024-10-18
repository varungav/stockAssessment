package com.stockAssigment.stockAssigment.repo;

import com.stockAssigment.stockAssigment.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
