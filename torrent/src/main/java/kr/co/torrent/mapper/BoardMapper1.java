package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.PageVO;

public interface BoardMapper1 {
	public int selectBoardCount(PageVO page);
	public List<BoardVO> selectBoard(PageVO page);
	public List<BoardVO> selectBoardByNo(int bno);
	public int deleteBoard(int bno);
	public int insertBoard(BoardVO board);
	public int updateBoard(BoardVO board);
}
