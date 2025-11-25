package com.example.usermanager.service;

import com.example.usermanager.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    boolean update(User user);

    boolean delete(Long id);

     List<User> search(String keyword, String tag);
     boolean updateTags(Long id, String tags);

    // TODO：完成新增用户接口
}
