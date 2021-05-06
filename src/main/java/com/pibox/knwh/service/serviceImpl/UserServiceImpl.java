package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.DTO.UserDTO;
import com.pibox.knwh.entity.User;
import com.pibox.knwh.exception.domain.BadRequestException;
import com.pibox.knwh.exception.domain.NotFoundException;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.repository.UserRepository;
import com.pibox.knwh.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserById(Long id) {
        checkIfExistsById(id);
        return modelMapper.map(userRepository.findUserById(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUsers(Long companyId) {
        List<User> users = userRepository.findAllByCompanyId(companyId);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(UserDTO userDTO) {
        checkIfEmailExists(userDTO);
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (!userDTO.getEmail().equals(userRepository.findUserById(id).getEmail())) {
            checkIfEmailExists(userDTO);
        }
        User user = userRepository.findUserById(id);
        modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> mapper.skip(User::setId));
        modelMapper.map(userDTO, user);
        user.setActive(true);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        checkIfExistsById(id);
        userRepository.deleteById(id);
    }

    private void checkIfExistsById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(
                    "User with ID: " + id + " is not found"
            );
        }
    }

    private void checkIfEmailExists(UserDTO userDTO) {
        if (userRepository.selectExistsEmail(userDTO.getEmail())) {
            throw new BadRequestException(
                    "Email " + userDTO.getEmail() + " taken"
            );
        }
    }
}
