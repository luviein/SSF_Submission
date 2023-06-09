package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp2023.batch3.ssf.frontcontroller.model.User;

@Controller
public class LandingController {
    @GetMapping(path="/")
	public String getLanding(Model m){
        m.addAttribute("user", new User());
		return "view0";
	}
}
