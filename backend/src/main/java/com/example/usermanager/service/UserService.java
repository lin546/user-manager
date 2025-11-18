package com.example.usermanager.service;

import com.example.usermanager.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void create(User user);

    boolean update(User user);

    boolean delete(Long id);
}
