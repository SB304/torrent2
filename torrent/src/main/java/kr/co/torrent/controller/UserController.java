package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.torrent.service.UserService;
import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/loginForm")
	public void loginForm(){
	}
	
	@RequestMapping("/login")
	public String login(LoginDTO login,HttpSession session){
		System.out.println(login.getId());
		System.out.println(login.getPassword());
		service.login(login, session);
		return "redirect:/";
	}
	
	@RequestMapping("/joinForm")
	public void joinForm(){
	}

	@RequestMapping("/join")
	public String join(UserVO user){
		
		service.join(user);
		return "redirect:loginForm";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
