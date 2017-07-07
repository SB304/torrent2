package kr.co.torrent.service;

import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

public interface UserService {
	
	public void join(UserVO user);
	public String login(LoginDTO login);
	public String idCheck(String id);
}

