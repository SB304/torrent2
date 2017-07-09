package kr.co.torrent.service;

import java.util.List;
import java.util.Map;

import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.PageVO;

public interface BoardService {
	public Map<String, Object> list(PageVO page) ;
	public List<BoardVO> detail(int bno);
	public int delete(int bno);
	public int insert(BoardVO board);
	public int update(BoardVO board);
}
