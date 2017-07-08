package kr.co.torrent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.torrent.service.BoardService2;
import kr.co.torrent.vo.BoardVO;

@RestController
@RequestMapping("/board2")
public class BoardController2 {

	@Autowired
	private BoardService2 service;
	
	@RequestMapping("/readBoardList.json")
	public List<BoardVO> readBoardList(int genre){
		System.out.println("readBoardList");
		return service.readBoardList(genre);
	}
	
	@RequestMapping("/readBoard.json")
	public BoardVO readBoard(int bno) {
		return service.readBoard(bno);
	}
	
	@RequestMapping("/modifyBoard.json")
	public void modifyBoard(BoardVO board) {
		service.modifyBoard(board);
	}
	
	@RequestMapping("/removeBoard.json")
	public void removeBoard(int bno) {
		service.removeBoard(bno);
	}
}
