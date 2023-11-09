package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.form.UserForm;
import com.project.mapper.UserMapper;
import com.project.service.UserService;

/**
 * ユーザに関わるServiceクラス
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public void regist(UserForm userForm) {
		
		// MapperインターフェースでFormの値を登録する
		userMapper.insertUser(userForm);
	}

}
