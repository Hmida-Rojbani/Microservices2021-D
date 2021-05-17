package de.tekup.service.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;

@Entity
@Data
public class UserEntity {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 45, nullable = false)
	private String name;
	
	@Column(length = 45, nullable = false, unique = true)
	private String email;
	
	private String userId;
	
	private String password;
	
	public void setPassword(String plainPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(plainPassword);
	}
	
}
