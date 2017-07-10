package kr.co.torrent.service;

import java.util.List;
import java.util.Map;

import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageVO;

public interface BoardService {
	public Map<String, Object> list(PageVO page) ;
	public List<BoardVO> detail(int bno);
	public void delete(int bno);
	public Map<String, Object> insert(BoardVO board, FileVO file);
	public int update(BoardVO board);
	public int likeCount(int bno);
	public int doLike(LikeVO like);
}
