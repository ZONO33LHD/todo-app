package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;

import com.project.entity.UserEntity;
import com.project.form.UserForm;

@Mapper
public interface UserMapper {
	
	@ResultType(UserEntity.class)
	List<UserEntity> selectUsers(String userName);
	
	void insertUser(UserForm userForm);

}
