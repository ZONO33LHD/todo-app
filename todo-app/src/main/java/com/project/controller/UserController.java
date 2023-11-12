package com.project.controller;

import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String signup(Model model, Locale locale, @ModelAttribute UserForm form) {

		return "user/signup";
	}

	/**
	 * ユーザのログイン処理
	 */
	@PostMapping("/login")
	public String loginProcess(Model model, Locale locale, @Validated @ModelAttribute UserForm userForm,
			BindingResult bindingResult) {
		log.info(userForm.toString());

		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/login";

		}
		// ログイン処理
		boolean loginResult = userService.loginProcess(userForm);

		if (loginResult) {
			// ログインが成功した場合の処理
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				model.addAttribute("username", userDetails.getUsername());
			}

			return "top"; // ログイン成功時のリダイレクト
		} else {
			// ログインが失敗した場合の処理
			model.addAttribute("ValidationErrors", bindingResult.getAllErrors());
			model.addAttribute("loginError", "ユーザ名またはパスワードが正しくありません");
			return "user/login";
		}

	}

	/**
	 * ユーザの登録処理
	 */
	@PostMapping("/signup")
	public String register(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult, Model model) {
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/signup"; // ユーザ作成処理を実行せずに登録画面に戻る
		}

		try {
			userService.regist(userForm);
		} catch (Exception e) {
			bindingResult.rejectValue("userName", "require_check", "ユーザ名は必須入力です");
			bindingResult.rejectValue("password", "require_check", "パスワードは必須入力です");
			model.addAttribute("userForm", userForm);
			model.addAttribute("errorMessage", "ユーザの登録中にエラーが発生しました"); // エラーメッセージを設定

			// もしくは、リダイレクトまたはエラーページに遷移するなど、エラーハンドリングを適切に行う
			return "user/signup";
		}

		// リダイレクト先を設定
		return "top";
	}

	/**
	 * ログイン画面の遷移をコントロール
	 */
	@GetMapping("/login")
	public String login(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "user/login";
	}

}
