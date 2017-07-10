package kr.co.torrent.service;

import java.util.Map;

import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageVO;

public interface BoardService {
	public Map<String, Object> list(PageVO page) ;
	public Map<String, Object> detail(int bno);
	public void delete(int bno);
	public void insert(BoardVO board, FileVO file);
	public void update(BoardVO board, FileVO file);
	public int likeCount(int bno);
	public int doLike(LikeVO like);
}
