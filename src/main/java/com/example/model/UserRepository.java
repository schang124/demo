package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by schang124 on 2016/11/28.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
