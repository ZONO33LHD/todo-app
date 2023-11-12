package com.project.service;

import com.project.form.UserForm;

public interface UserService {
	
	boolean loginProcess(UserForm userForm);
		
	void regist(UserForm userForm);

}
