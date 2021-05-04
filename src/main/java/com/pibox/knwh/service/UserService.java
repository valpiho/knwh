package com.pibox.knwh.service;

import com.pibox.knwh.entity.DTO.UserDTO;
import com.pibox.knwh.entity.User;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    void addUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
