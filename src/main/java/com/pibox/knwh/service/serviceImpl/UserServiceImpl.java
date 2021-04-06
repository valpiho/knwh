package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.User;
import com.pibox.knwh.exception.BadRequestException;
import com.pibox.knwh.exception.UserNotFoundException;
import com.pibox.knwh.repository.UserRepository;
import com.pibox.knwh.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        Boolean existsEmail = userRepository.selectExistsEmail(user.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + user.getEmail() + " taken"
            );
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(
                    "User with id " + userId + " does not exists"
            );
        }
        userRepository.deleteById(userId);
    }
}
