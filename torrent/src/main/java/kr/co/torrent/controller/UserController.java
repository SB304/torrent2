package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.torrent.service.UserService;
import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	
	@RequestMapping("/login.json")
	public String login(LoginDTO login,HttpSession session){
		System.out.println(login.getId());
		System.out.println(login.getPassword());
		service.login(login,session);
		
		return "";
	}

	@RequestMapping("/join.json")
	public String join(UserVO user){
		System.out.println(user.getEmail());
		System.out.println(user.getId());
		System.out.println(user.getPassword());
		service.join(user);
		return "success";
	}
	
	@RequestMapping("/logout.json")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
