package kr.co.torrent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.BoardMapper2;
import kr.co.torrent.vo.BoardVO;

@Service
public class BoardServiceImpl2 implements BoardService2{
	
	@Autowired
	private BoardMapper2 mapper;

	@Override
	public void writeBoard(BoardVO board) {
		mapper.insertBoard(board);
	}

	@Override
	public List<BoardVO> readBoardList(int genre) {
		return mapper.selectBoardList(genre);
	}

	@Override
	public BoardVO readBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	@Override
	public void modifyBoard(BoardVO board) {
		mapper.updateBoard(board);
	}

	@Override
	public void removeBoard(int bno) {
		mapper.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> homeData(){
		return mapper.selectPopularList();
	}

}
