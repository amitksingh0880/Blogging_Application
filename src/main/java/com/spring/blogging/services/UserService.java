package com.spring.blogging.services;

import java.util.List;
import com.spring.blogging.payloads.UserDto;

public interface UserService {

	 UserDto createUser(UserDto user);
	 UserDto UpdateUser(UserDto user , Integer userId);
	 UserDto getUserById(Integer userId);
	 List<UserDto> getAllUsers();
	 void deleteUser(Integer userId);
}
