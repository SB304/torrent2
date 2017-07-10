package kr.co.torrent.service;

import java.util.List;

import kr.co.torrent.vo.BoardVO;

public interface BoardService2 {
	
	public void writeBoard(BoardVO board);
	public List<BoardVO> readBoardList(int genre);
	public BoardVO readBoard(int bno);
	public void modifyBoard(BoardVO board);
	public void removeBoard(int bno);
	public List<BoardVO> homeData();
}
