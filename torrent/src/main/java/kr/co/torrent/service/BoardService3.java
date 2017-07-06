package kr.co.torrent.service;

import java.util.List;
import java.util.Map;

import kr.co.torrent.vo.BoardCommentVO;
import kr.co.torrent.vo.BoardFileVO;
import kr.co.torrent.vo.BoardRecommendVO;
import kr.co.torrent.vo.BoardVO;

public interface BoardService3 {
	//////////보드서비스/////////////////////
	public BoardVO updateForm(int no) throws Exception;
	public void delete(int no) throws Exception;
	public void update(BoardVO board) throws Exception;
	public void insert(BoardVO board, BoardFileVO file) throws Exception;
	public List<BoardVO> select() throws Exception; 
	public Map<String, Object> detail(int no) throws Exception;
	/////////댓글 서비스//////////////////////
	public void deleteComment(int no) throws Exception;
	public void updateComment(BoardCommentVO comment) throws Exception;
	public void insertComment(BoardCommentVO comment) throws Exception;
	public List<BoardCommentVO> selectCommentByNo(int no) throws Exception; 
	/////////추천수 서비스//////////////////////
	public BoardRecommendVO checkRecommend(BoardRecommendVO recommend) throws Exception;
	public int countRecommend(int no) throws Exception;
	public void deleteRecommend(BoardRecommendVO recommend) throws Exception;
	public void insertRecommend(BoardRecommendVO recommend) throws Exception;
}
