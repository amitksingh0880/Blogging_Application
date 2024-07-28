package com.spring.blogging.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id; // Changed from Id to id for consistency with naming conventions

	@NotNull
	private String name;

	@Email(message = "Email is inValid !")
	private String email;

	@NotEmpty
	@Size(min = 3 , max = 10 ,message = "Password must be of min 3 and max 10 characters !")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
			message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;

	@NotEmpty
	private String about;
}
