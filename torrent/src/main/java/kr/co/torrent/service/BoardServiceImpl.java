package kr.co.torrent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.BoardMapper1;
import kr.co.torrent.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper1 mapper;

	@Override
	public List<BoardVO> list() {
		return mapper.selectBoard();
	}
	
	@Override
	public List<BoardVO> detail(int bno) {
		return mapper.selectBoardByNo(bno);
	}
	
	@Override
	public int delete(int bno) {
		return mapper.deleteBoard(bno);
	}

	@Override
	public int insert(BoardVO board) {
		return mapper.insertBoard(board);
	}

	@Override
	public int update(BoardVO board) {
		return mapper.updateBoard(board);
	}

}
