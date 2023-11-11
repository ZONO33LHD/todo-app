package com.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.form.UserForm;

@Mapper
public interface UserMapper {

	
	void insertUser(UserForm userForm);

}
