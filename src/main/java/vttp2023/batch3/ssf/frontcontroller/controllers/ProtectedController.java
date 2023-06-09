package vttp2023.batch3.ssf.frontcontroller.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.ssf.frontcontroller.model.User;

@Controller
@RequestMapping(path = "/protected")

public class ProtectedController {


	@GetMapping(path ="/{string}")
	public String isProtected(HttpSession session, @PathVariable String string){

		User u = (User) session.getAttribute("newUser");
		if(u.isAuthenticated()){
			return"view1";
		}
		return "view0";

	}
	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
}
