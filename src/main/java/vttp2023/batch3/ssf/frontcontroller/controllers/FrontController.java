package vttp2023.batch3.ssf.frontcontroller.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller

public class FrontController {

	@Autowired
	private AuthenticationService authSvc;

	// TODO: Task 2, Task 3, Task 4, Task 6
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(Model m, HttpSession session, @Valid User user, BindingResult result,
			@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		if (result.hasErrors()) {
			return "view0";
		}

		session.setAttribute("newUser", user);
		if (authSvc.authenticate(username, password)) {

			return "view1";
		}

		if (user.getWrongCount() >= 1) {
			String userCaptcha = user.generateCaptcha();
			m.addAttribute("captcha", userCaptcha);
			if (user.getWrongCount() > 3) {
				authSvc.disableUser(username);
			}
		}
		return "view0";
	}

}
