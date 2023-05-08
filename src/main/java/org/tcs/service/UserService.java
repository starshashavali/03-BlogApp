package org.tcs.service;

import org.tcs.binding.LoginForm;
import org.tcs.binding.RegistrationForm;

public interface UserService {
	public boolean userRegistration(RegistrationForm signup);

	public boolean loginVerify(LoginForm login);

}
