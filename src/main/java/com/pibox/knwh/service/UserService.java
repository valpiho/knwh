package com.pibox.knwh.service;

import com.pibox.knwh.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long personId);
}
