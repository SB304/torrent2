package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.BoardVO;

public interface BoardMapper2 {
	public void insertBoard(BoardVO board);
	public List<BoardVO> selectBoardList(int genre);
	public BoardVO selectBoard(int bno);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int bno);
}
