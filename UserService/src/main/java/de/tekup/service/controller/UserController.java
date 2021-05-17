package de.tekup.service.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.service.data.services.UserService;
import de.tekup.service.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	
	private Environment env;
	private UserService userService;
	
	@GetMapping
	public String works() {
		return "User service works on port :" +env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){
		UserDTO user = userService.saveUserToDB(userDTO) ;
		if(user != null)
			return ResponseEntity.status(HttpStatus.CREATED)
								.body("user is add to DB with user-id :"+user.getUserId());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("Something is wrong");
	}
}
