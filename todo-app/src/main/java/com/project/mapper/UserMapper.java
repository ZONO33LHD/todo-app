package com.project.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.form.UserForm;

@Mapper
public interface UserMapper {

	@Insert("INSERT INTO USER_MASTER (USER_NAME, USER_MASTER) VALUES (#{userForm.userName}, #{userForm.password})")
	void insertUser(@Param("userForm") UserForm userForm);
}
