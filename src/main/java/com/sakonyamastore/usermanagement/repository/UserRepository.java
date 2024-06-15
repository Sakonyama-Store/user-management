package com.sakonyamastore.usermanagement.repository;

import com.sakonyamastore.usermanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "select user from UserEntity user " +
            "where user.email = :email or user.username = :username")
    Optional<UserEntity> findByEmailOrUserName(@Param("email") String email, @Param("username") String username);
}
