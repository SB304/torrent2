package kr.co.torrent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.torrent.service.BoardService2;
import kr.co.torrent.vo.BoardVO;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService2 service;
	
	
	@RequestMapping("/auth")
	public String auth() {
		System.out.println("asdasdasdasdasdasdsadasdas");
		return "user/loginForm.html";
	}
	
	
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
	
	@RequestMapping("/getSessionId")
	@ResponseBody
	public String getSessionId(HttpSession session) {
		return (String)session.getAttribute("user");
	}

	@RequestMapping("/homeData.json")
	@ResponseBody
	public List<BoardVO> homeData(){
		
		List<BoardVO> list = service.homeData();
		for(BoardVO board : list) {
			System.out.println(board.getContent());
			
			String content = board.getContent();
			board.setContent(content.substring(content.indexOf("<img"),content.indexOf("</p>")));
			System.out.println(content.substring(content.indexOf("<img"),content.indexOf("</p>")));
		}
		
		return list;
	}
}
