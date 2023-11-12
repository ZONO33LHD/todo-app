package com.project.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザ情報保持フォームクラス
 * 
 * @author nakazono
 */
@Data
public class UserForm {
	
	@Size(max=20)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@NotBlank
	private String userName;
	
	@Size(max=64)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@NotBlank
	private String password;
	
}
