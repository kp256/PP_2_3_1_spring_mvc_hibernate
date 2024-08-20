package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(Model model) {
		model.addAttribute("messages", "Hello page");
		return "index";
	}
	
}