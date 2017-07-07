package kr.co.torrent.service;

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
	public String login(LoginDTO login) {
		return mapper.selectUser(login);
	}

	@Override
	public String idCheck(String id) {
		return mapper.selectId(id);
	}

}
