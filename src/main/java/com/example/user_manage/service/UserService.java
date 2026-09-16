package com.example.user_manage.service;

import com.example.user_manage.dto.request.UserReq;
import com.example.user_manage.entity.User;

import java.util.List;

public interface UserService {
     User getById(Long id);
     List<User> getAll();
     User createUser(UserReq request);
     User updateUser(Long id, UserReq request);
     void deleteUser(Long id);
     List<User> filter(String userName,String firstName,String lastName,Long minAge,Long maxAge);
     void updateUserByFirstName();
     void deleteUserByFirstName();
}
