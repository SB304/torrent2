package kr.co.torrent.service;

import java.util.List;

import kr.co.torrent.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> list() ;
	public List<BoardVO> detail(int bno);
	public int delete(int bno);
	public int insert(BoardVO board);
	public int update(BoardVO board);
}
