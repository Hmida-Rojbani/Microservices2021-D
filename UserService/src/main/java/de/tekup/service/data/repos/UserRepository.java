package de.tekup.service.data.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import de.tekup.service.data.models.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String username);
}
