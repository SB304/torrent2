package kr.co.torrent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.torrent.service.BoardService;
import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.PageVO;


@RestController
public class BoardController1 {
	
	@Autowired
	private BoardService boardService;
	
	// 리스트
	@RequestMapping("/board1list.json")
	public List<BoardVO> list(PageVO page){
		return boardService.list(page);
	}
	
	// 상세보기
	@RequestMapping("/board1detail.json")
	public List<BoardVO> detail(int bno){
		return boardService.detail(bno);
	}
	
	// 삭제
	@RequestMapping("/board1delete.json")
	public void delete(int bno){
		boardService.delete(bno);
	}
	
	// 수정
	@RequestMapping("/board1update.json")
	@Transactional(rollbackFor=Exception.class)
	public void Update(BoardVO board) throws Exception{
		boardService.update(board);
	}
	
	// 등록
	@RequestMapping("/board1write.json")
	@Transactional(rollbackFor=Exception.class)
	public void write(BoardVO board){
		boardService.insert(board);
	}
}
