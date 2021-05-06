package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.DTO.UserDTO;
import com.pibox.knwh.entity.User;
import com.pibox.knwh.exception.domain.BadRequestException;
import com.pibox.knwh.exception.domain.NotFoundException;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.repository.UserRepository;
import com.pibox.knwh.service.UserService;
import org.modelmapper.ModelMapper;
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
        checkIfUserExistsById(id);
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
        checkIfEmailExists(userDTO.getEmail());
        User user = modelMapper.map(userDTO, User.class);
        checkIfCompanyExists(user.getCompany().getId());
        user.setCreatedAt(new Date());
        userRepository.save(user);
    }

    private void checkIfCompanyExists(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(
                    "Company with ID: '" + id + "' is not found"
            );
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        checkIfUserExistsById(id);
        if (!userDTO.getEmail().equals(userRepository.findUserById(id).getEmail())) {
            checkIfEmailExists(userDTO.getEmail());
        }
        User user = userRepository.findUserById(id);
        modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> mapper.skip(User::setId));
        modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> mapper.skip(User::setCompany));
        modelMapper.map(userDTO, user);
        user.setActive(true);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        checkIfUserExistsById(id);
        userRepository.deleteById(id);
    }

    private void checkIfUserExistsById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(
                    "User with ID: " + id + " is not found"
            );
        }
    }

    private void checkIfEmailExists(String email) {
        if (userRepository.selectExistsEmail(email)) {
            throw new BadRequestException(
                    "Email " + email + " taken"
            );
        }
    }
}
