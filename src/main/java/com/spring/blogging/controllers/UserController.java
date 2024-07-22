package com.spring.blogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blogging.payloads.ApiResponse;
import com.spring.blogging.payloads.UserDto;
import com.spring.blogging.services.UserService;


@RestController
@RequestMapping("/api/users")

public class UserController {
     
    @Autowired
	private UserService userService; // class

	//POST -- create user-------
        @PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createuserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createuserDto , HttpStatus.CREATED );
	}
	
	//PUT -- Update user -------
        @PatchMapping("/{userId}")
        public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
            UserDto updatedUser = this.userService.UpdateUser(userDto, userId);
            return ResponseEntity.ok(updatedUser);
        }
	
	//DELETE -- delete user ------
        @DeleteMapping("/{userId}")
        public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer uid)
        {
        	this.userService.deleteUser(uid);
        	return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
        }
	
	//GET -- user get--------
        @GetMapping("/")
        public ResponseEntity<List<UserDto>> getAllUsers()
    	{
    		return ResponseEntity.ok(this.userService.getAllUsers());
    	}
        
    //GET ---- user single -------
        @GetMapping("/{userId}")
        public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId)
    	{
    		return ResponseEntity.ok(this.userService.getUserById(userId));
    	}
        
}
