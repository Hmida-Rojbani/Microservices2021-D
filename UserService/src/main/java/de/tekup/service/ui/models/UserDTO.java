package de.tekup.service.ui.models;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	
	@Size(min=2 , message = "User name should have more than 2 charcters")
	private String name;
	
	@NotBlank(message = "email should not be empty")
	@Email
	private String email;
	
	@Pattern(regexp = "$[0-9]{4-8}^")
	private String password;
	
	private String userId;
	
	private List<AlbumResponseModel> albums;

}
