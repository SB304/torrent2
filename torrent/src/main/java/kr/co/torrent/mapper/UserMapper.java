package kr.co.torrent.mapper;

import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

public interface UserMapper {
	
	public void insertUser(UserVO user); 
	public String selectUser(LoginDTO login);
	public String selectId(String id);
}
