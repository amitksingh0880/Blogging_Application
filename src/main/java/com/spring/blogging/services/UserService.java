package com.spring.blogging.services;

import java.util.List;

import com.spring.blogging.payloads.RegisterUserDto;
import com.spring.blogging.payloads.UserDto;

public interface UserService {

	UserDto createUser(RegisterUserDto registerUserDto);
	UserDto UpdateUser(UserDto userDto, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
