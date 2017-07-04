package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/loginForm")
	public void loginForm(){
		System.out.println("loginForm");
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session){
		//로그인후 세션에 등록
		session.setAttribute("", "");
		return "redirect:/";
	}
	
	@RequestMapping("/joinForm")
	public void joinForm(){
		System.out.println("joinForm");
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm.do";
	}
}
