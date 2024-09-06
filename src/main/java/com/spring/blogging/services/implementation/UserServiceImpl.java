package com.spring.blogging.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.blogging.exceptions.ResourceNotFoundException;
import com.spring.blogging.entities.User;
import com.spring.blogging.payloads.RegisterUserDto;
import com.spring.blogging.payloads.UserDto;
import com.spring.blogging.repositories.UserRepo;
import com.spring.blogging.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(RegisterUserDto registerUserDto) {
        // Map RegisterUserDto to User entity
        User user = this.modelMapper.map(registerUserDto, User.class);

        // Ensure ID is null for new user (to avoid updating an existing user)
        user.setId(null);

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        // Save the user in the repository
        User savedUser = this.userRepo.save(user);

        // Map the saved User entity to UserDto and return it
        return this.modelMapper.map(savedUser, UserDto.class);
    }


    @Override
    public UserDto UpdateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        try {
            return users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        }catch (Exception e) {
            if (users.size() == 1) {
                return (List<UserDto>) users.get(0);
            } else if (users.isEmpty()) {
                throw new RuntimeException("No user found with the email");
            } else {
                throw new RuntimeException("Multiple users found with the same email");
            }
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
}
