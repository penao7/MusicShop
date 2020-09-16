package oinonen.MusicStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
 
 @GetMapping("login-error")
 public String loginError(Model model) {
		String errorMessage = "Wrong username or password";
		model.addAttribute("errorMessage", errorMessage);
		return "login";
 }
 
 @GetMapping("login-success")
 public String loginSuccess(Model model) {
		String message = "Logged in succesfully";
		model.addAttribute("message", message);
		return "index";
 }
 
 @GetMapping("logout-success")
 public String logoutSuccess(Model model) {
		String message = "Logged out succesfully";
		model.addAttribute("message", message);
		return "index";
 };
};
