package kr.co.torrent.service;

import java.util.List;
import java.util.Map;

import kr.co.torrent.vo.ReplyVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.BoardVO;

public interface BoardService3 {
	//////////보드서비스/////////////////////
	public BoardVO updateForm(int no) throws Exception;
	public void delete(int no) throws Exception;
	public void update(BoardVO board) throws Exception;
	public void insert(BoardVO board, FileVO file) throws Exception;
	public List<BoardVO> select(int genre) throws Exception; 
	public Map<String, Object> detail(int no) throws Exception;
	/////////댓글 서비스//////////////////////
	public void deleteComment(int no) throws Exception;
	public void updateComment(ReplyVO comment) throws Exception;
	public void insertComment(ReplyVO comment) throws Exception;
	public List<ReplyVO> selectReplyByNo(int no) throws Exception; 
	/////////추천수 서비스//////////////////////
	public LikeVO checkRecommend(LikeVO recommend) throws Exception;
	public int countRecommend(int no) throws Exception;
	public void deleteRecommend(LikeVO recommend) throws Exception;
	public void insertRecommend(LikeVO recommend) throws Exception;
}
