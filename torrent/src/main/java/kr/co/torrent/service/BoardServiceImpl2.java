package kr.co.torrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.BoardMapper2;

@Service
public class BoardServiceImpl2 implements BoardService2{
	
	@Autowired
	private BoardMapper2 mapper;


}
