package org.tcs.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcs.binding.LoginForm;
import org.tcs.binding.RegistrationForm;
import org.tcs.entity.UserEntity;
import org.tcs.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private HttpSession session;


	@Override
	public boolean userRegistration(RegistrationForm signup) {

		UserEntity findByEmail = userRepo.findByEmail(signup.getEmail());

		if (findByEmail != null) {
			return false;

		}

		UserEntity entity = new UserEntity();

		BeanUtils.copyProperties(signup, entity);

		userRepo.save(entity);

		return true;
	}
	

	@Override
	public boolean loginVerify(LoginForm login) {

		UserEntity entity = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (entity == null) {
			return false;
		}

		// create session and store user data in session

		session.setAttribute("userId", entity.getUserId());

		return true;

	}
	}
	

