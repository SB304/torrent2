package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.BoardCommentVO;
import kr.co.torrent.vo.BoardFileVO;
import kr.co.torrent.vo.BoardRecommendVO;
import kr.co.torrent.vo.BoardVO;

public interface BoardMapper3 {
	public void insertBoard(BoardVO board) throws Exception;
	public int updateBoard(BoardVO board) throws Exception;
	public int deleteBoard(int no) throws Exception;
	public List<BoardVO> selectBoard() throws Exception;
	public BoardVO selectBoardByNo(int no) throws Exception;

	// 파일 작업
	public void insertBoardFile(BoardFileVO fileVO) throws Exception;
	public BoardFileVO selectBoardFileByNo(int fileNo) throws Exception;
	
	// 댓글 작업
	public List<BoardCommentVO> selectBoardCommentByNo(int no) throws Exception;
	public void insertBoardComment(BoardCommentVO comment) throws Exception;
	public void deleteBoardComment(int commentNo) throws Exception;
	public void updateBoardComment(BoardCommentVO comment) throws Exception;
	//추천수 작업
	public int countBoardRecommend(int boardNo) throws Exception;
	public BoardRecommendVO checkRecommend(BoardRecommendVO recommend) throws Exception;
	public void insertBoardRecommend(BoardRecommendVO recommend);
	public void deleteBoardRecommend(BoardRecommendVO recommend);
}
