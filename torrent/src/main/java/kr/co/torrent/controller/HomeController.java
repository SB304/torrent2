package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.torrent.vo.UserVO;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public void home() {
		System.out.println("home");
	}

	@RequestMapping("/loginConfirm")
	@ResponseBody
	public String loginConfirm(HttpSession session) {

		System.out.println("loginConfirm");

		String result = "";
		UserVO user = (UserVO) session.getAttribute("user");
		if (user == null) {
			result += "<button class='btn btn_default' id='loginBtn'>로그인</button>";
		} else {
			result += "<button class='btn btn_default' id='logoutBtn'>로그아웃</button>";
		}

		return result;
	}

}