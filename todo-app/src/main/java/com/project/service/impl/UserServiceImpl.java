package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dao.UserMapper;
import com.project.entity.UserEntity;
import com.project.form.UserForm;
import com.project.service.UserService;

/**
 * ユーザに関わるServiceクラス
 * 
 * @author nakazono
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * ユーザのログイン処理
	 */
	@Override
	public boolean loginProcess(UserForm userForm) {

	    // パスワードのハッシュ化
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	    // ユーザ名に合致するユーザ情報を取得
	    List<UserEntity> userInfoList = userMapper.selectUsers(userForm.getUserName());

	    // 画面側から入力された情報がNULLの場合またはユーザ情報が取得できなかった場合
	    if (userForm == null || userInfoList == null) {
	        return false;
	    }

	    for (UserEntity userInfo : userInfoList) {
	            // パスワードを検証
	            if (passwordEncoder.matches(userForm.getPassword(), userInfo.getPassword())) {
	                return true;
	            }
	    }

	    // ユーザ名とパスワードの組み合わせが見つからない場合
	    return false;
	}
	
	
	/**
	 * ユーザ登録処理
	 */
	@Override
	public void regist(UserForm userForm) {
		// パスワードをハッシュ化した後、MapperインターフェースでFormの値を登録する
		UserForm form = new UserForm();
		form.setUserName(userForm.getUserName());
		form.setPassword(saltedHashPassword(userForm));
		
		userMapper.insertUser(form);
	}

	// パスワードのハッシュ化(Bcrypt)関数
	public String saltedHashPassword(UserForm userForm) {
		String password = userForm.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = bCryptPasswordEncoder.encode(password);
		
		return hashedPassword;
	}

}
