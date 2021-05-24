package de.tekup.service.data.services;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.tekup.service.data.models.UserEntity;
import de.tekup.service.data.repos.UserRepository;
import de.tekup.service.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

	private ModelMapper mapper;
	private UserRepository repository;
	
	public UserDTO saveUserToDB(UserDTO userDTO) {
		UserEntity user = mapper.map(userDTO, UserEntity.class);
		user.setUserId(UUID.randomUUID().toString());
		UserEntity userSaved = repository.save(user);
		
		return mapper.map(userSaved,UserDTO.class);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity =repository.findByEmail(username)
										.orElseThrow(()-> new UsernameNotFoundException("User Email not found" ));
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
	}
	
	public UserDTO findUserByEmail(String email) {
		UserEntity userEntity =repository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("User Email not found" ));
		return mapper.map(userEntity, UserDTO.class);
	}

}
