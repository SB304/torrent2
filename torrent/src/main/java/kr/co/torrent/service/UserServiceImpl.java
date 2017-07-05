package kr.co.torrent.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.UserMapper;
import kr.co.torrent.vo.LoginDTO;
import kr.co.torrent.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void join(UserVO user) {
		mapper.insertUser(user);
	}

	@Override
	public void login(LoginDTO login, HttpSession session) {
		System.out.println(login.getId());
		System.out.println(login.getPassword());
		
		UserVO user = mapper.selectUser(login);
		session.setAttribute("user", user);
		
	}

}
