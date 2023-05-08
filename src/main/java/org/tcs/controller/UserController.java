package org.tcs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tcs.binding.RegistrationForm;
@Controller
public class UserController {

	@Autowired
	private org.tcs.service.UserService service;

	@Autowired
	private org.tcs.repo.PostRepo postRepo;

	@Autowired
	private HttpSession postSession;
	
	@GetMapping("/register")

	public String loadRegister(Model model) {

		model.addAttribute("signup", new RegistrationForm());

		return "register";

	}


	@PostMapping("/register")
	public String registration(@ModelAttribute("signup") org.tcs.binding.RegistrationForm form, Model model) {

		boolean status = service.userRegistration(form);

		if (status) {
			model.addAttribute("succMsg", "Sign up Successfully");
		} else {
			model.addAttribute("errMsg", "erorr occured..");
		}

		model.addAttribute("signup", new org.tcs.binding.RegistrationForm());

		return "register";

	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("loginForm", new org.tcs.binding.LoginForm());
		return "login";

	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") org.tcs.binding.LoginForm form, Model model) {

		boolean status = service.loginVerify(form);

		if (status) {
			return "redirect:/dashboard";
		} else {

			model.addAttribute("errMsg", "Invalid credentials");

			return "login";
		}

	}
	@GetMapping("/")
	public String loadIndexPage(Model model) {

		return "index";
	}
}
