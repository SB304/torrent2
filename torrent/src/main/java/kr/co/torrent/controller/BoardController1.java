package kr.co.torrent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.torrent.service.BoardService;
import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageVO;

@RestController
public class BoardController1 {

	@Autowired
	private BoardService boardService;

	// 리스트
	@RequestMapping("/board1list.json")
	public Map<String, Object> list(PageVO page) {
		return boardService.list(page);
	}

	// 상세보기
	@RequestMapping("/board1detail.json")
	public List<BoardVO> detail(int bno) {
		return boardService.detail(bno);
	}

	// 삭제
	@RequestMapping("/board1delete.json")
	public void delete(int bno) {
		boardService.delete(bno);
	}

	// 수정
	@RequestMapping("/board1update.json")
	@Transactional(rollbackFor=Exception.class)
	public void Update(BoardVO board) {
		boardService.update(board);
		
	}
	
	// 등록
	@RequestMapping("/board1write.json")
	public Map<String, Object> write(BoardVO board, FileVO file) {
		return boardService.insert(board, file);
		}
	
	// 추천
	@RequestMapping("/board1like.json")
	public int boardLike(LikeVO like){
		return boardService.doLike(like);
	}
	
	// 추천 수
	@RequestMapping("/board1likecount.json")
	public int boardLikeCount(int bno){
		return boardService.likeCount(bno);
	}
	
}
