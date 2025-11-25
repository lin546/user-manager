package com.example.usermanager.service.impl;

import com.example.usermanager.entity.User;
import com.example.usermanager.mapper.UserMapper;
import com.example.usermanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void create(User user) {
        userMapper.insert(user);
    }

    @Override
    public boolean update(User user) {
        int rows = userMapper.update(user);
        return rows > 0;
    }

    @Override
    public boolean delete(Long id) {
        int rows = userMapper.delete(id);
        return rows > 0;
    }

    @Override
    public List<User> search(String keyword, String tag) {
        return userMapper.search(keyword, tag);
    }

    @Override
    public boolean updateTags(Long id, String tags) {
        int rows = userMapper.updateTags(id, tags);
        return rows > 0;
    }
}
