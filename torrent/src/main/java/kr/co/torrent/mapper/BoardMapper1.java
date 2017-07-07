package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.BoardVO;

public interface BoardMapper1 {
	public List<BoardVO> selectBoard();
	public List<BoardVO> selectBoardByNo(int bno);
	public int deleteBoard(int bno);
	public int insertBoard(BoardVO board);
	public int updateBoard(BoardVO board);
}
