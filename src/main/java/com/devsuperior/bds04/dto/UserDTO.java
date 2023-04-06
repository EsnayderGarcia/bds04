package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Long id;
    private String email;

    public UserDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
    }

    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
