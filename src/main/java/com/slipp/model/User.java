package com.slipp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by schang124 on 2016/11/24.
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, length=20, nullable=false)
    private String userId;

    @Column(length=20, nullable=false)
    private String password;

    @Column(length=20, nullable=false)
    private String name;

    @Column(length=30)
    private String email;

    // data compare & update
    public void update(User user){
        if(!matchPassword(user.password))
            throw new IllegalArgumentException("비밀번호가 다릅니다");

        this.name = user.name;
        this.email = user.email;
    }

    // id check
    public boolean matchId(Long newId) {
        return this.id.equals(newId);
    }

    // password check
    public boolean matchPassword(String newPassword) {
        if(this.password == null)
            return false;

        return password.equals(newPassword);
    }


    // getter & setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User[id=" + id + "userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }



}
