package com.slipp.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by schang124 on 2016/11/28.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
