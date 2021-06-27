package com.example.jsp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JSPController {
	
	//model only
	@GetMapping("/testModelAndView")
	public String testModelAndView(Model model) { 
		Map<String, String> map = new HashMap<String, String>();
	    map.put("spring", "mvc");

		model.addAttribute("value", "arpit");
		model.mergeAttributes(map);
		return "hello";
	}
	
	//ModelAndView only
	@GetMapping("/helloView")
	public ModelAndView helloView(ModelAndView modelAndView) {
		modelAndView.setViewName("hello");
		return modelAndView;
	}
	
	//ModelAndMap only
	@GetMapping("/modelMap")
	public String modelAndMap(ModelMap modelMap) {
		modelMap.addAttribute("welcomeMessage", "welcome");
		modelMap.addAttribute("message", "Baeldung");
		return "hello";
	}
	
	//session management
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> msgs = (List<String>) session.getAttribute("MY_MESSAGES");

		if (msgs == null) {
			msgs = new ArrayList();
		}
		model.addAttribute("messages", msgs);

		return "index";
	}

	@PostMapping("/saveMessage")
	public String saveMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> msgs = (List<String>) request.getSession().getAttribute("MY_MESSAGES");
		if (msgs == null) {
			msgs = new ArrayList();
			request.getSession().setAttribute("MY_MESSAGES", msgs);
		}
		msgs.add(msg);
		request.getSession().setAttribute("MY_MESSAGES", msgs);
		return "redirect:/";
	}

	@PostMapping("/invalidate")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
