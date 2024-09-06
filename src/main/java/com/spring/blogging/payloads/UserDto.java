package com.spring.blogging.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotNull(message = "Name cannot be null")
	private String name;

	@Email(message = "Email is invalid")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
			message = "Password must be between 4 and 12 characters, with at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character")
	private String password;

	@NotEmpty(message = "About field cannot be empty")
	private String about;
}
