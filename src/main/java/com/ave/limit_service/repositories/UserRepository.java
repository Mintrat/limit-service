package com.ave.limit_service.repositories;

import com.ave.limit_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
//    public UserEntity findUserByUserId(String userId);
}
