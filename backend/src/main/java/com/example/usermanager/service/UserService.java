package com.example.usermanager.service;

import com.example.usermanager.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void create(User user);

    boolean update(User user);

    boolean delete(Long id);

    // TODO：新增查询和更新
     List<User> search(String keyword, String tag);
     boolean updateTags(Long id, String tags);

}
