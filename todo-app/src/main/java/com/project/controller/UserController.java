package com.project.controller;



import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.form.UserForm;
import com.project.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザに関わるControllerクラス
 * 
 * @author nakazono
 */
@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	/**
	 * 登録ページへ遷移
	 */
	@GetMapping("/signup")
	public ModelAndView signup(ModelAndView modelAndView) {
		modelAndView.setViewName("user/signup");
		modelAndView.addObject("userForm", new UserForm());
		
		return modelAndView;
	}
	
	/**
	 * ユーザの登録処理
	 */
	@PostMapping("/signup")
	public ModelAndView register(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("user/signup");
			modelAndView.addObject("userForm", userForm);
			return modelAndView;
		}
		// ユーザ作成処理
		userService.regist(userForm);
		
		// リダイレクト先を設定
		ModelAndView modelAndView = new ModelAndView("top");
		
		return modelAndView;
	}
	
	
	/**
	 * ログイン画面の遷移をコントロール
	 */
	@GetMapping
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.setViewName("user/login");
		modelAndView.addObject("userForm", new UserForm());
		return modelAndView;
	}

}
