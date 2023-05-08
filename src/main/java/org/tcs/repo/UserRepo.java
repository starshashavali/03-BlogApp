package org.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcs.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByEmail(String Email);

	public UserEntity findByEmailAndPassword(String email, String password);

}
