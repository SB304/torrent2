package kr.co.torrent.service;

import javax.servlet.http.HttpSession;

import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

public interface UserService {
	
	public void join(UserVO user);
	public void login(LoginDTO login, HttpSession session);
}
