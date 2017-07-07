package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		UserVO user = service.login(login);
		String result = "fail";
		if(user != null) {
			result = "success";
			session.setAttribute("user", user);
		}
		
		return result;
	}
	
	@RequestMapping("/naverLogin.json")
	public void naverLogin(String id,HttpSession session){
		session.setAttribute("user", id.substring(0,id.indexOf('@')));
	}
	


	@RequestMapping("/join.json")
	public String join(UserVO user){
		service.join(user);
		return "success";
	}
	
	@RequestMapping("/logout.json")
	public String logout(HttpSession session) {
		session.invalidate();
		return "success";
	}
	
	@RequestMapping("/idCheck.json")
	public String idCheck(String id) {
		System.out.println("idcheck");
		String check = service.idCheck(id);
		System.out.println(id);
		if(check == null) {
			return "true";
		}else {
			return "false";
		}
	}
}
