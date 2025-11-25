package com.example.usermanager.mapper;

import com.example.usermanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User findById(@Param("id") Long id);

    int update(User user);

    int delete(@Param("id") Long id);

     List<User> search(@Param("keyword") String keyword, @Param("tag") String tag);
    
     int updateTags(@Param("id") Long id, @Param("tags") String tags);

    // TODO：完成新增用户接口
}