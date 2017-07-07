package kr.co.torrent.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/loginConfirm.json")
	@ResponseBody
	public String loginConfirm(HttpSession session) {

		String result = "";
		Object user = session.getAttribute("user");
		if (user == null) {
			result += "<button class='btn btn_default' id='loginBtn'>로그인</button>"
					+ "<button class='btn btn_default' id='joinBtn'>회원가입</button>";
		} else {
			result += "<button class='btn btn_default' id='logoutBtn'>로그아웃</button>";
		}

		return result;
	}

}
