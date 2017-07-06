package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.ReplyVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.BoardVO;

public interface BoardMapper3 {
	public void insertBoard(BoardVO board) throws Exception;
	public int updateBoard(BoardVO board) throws Exception;
	public int deleteBoard(int no) throws Exception;
	public List<BoardVO> selectBoard() throws Exception;
	public BoardVO selectBoardByNo(int no) throws Exception;

	// 파일 작업
	public void insertBoardFile(FileVO fileVO) throws Exception;
	public FileVO selectBoardFileByNo(int fileNo) throws Exception;
	
	// 댓글 작업
	public List<ReplyVO> selectBoardCommentByNo(int no) throws Exception;
	public void insertBoardComment(ReplyVO comment) throws Exception;
	public void deleteBoardComment(int commentNo) throws Exception;
	public void updateBoardComment(ReplyVO comment) throws Exception;
	//추천수 작업
	public int countBoardRecommend(int boardNo) throws Exception;
	public LikeVO checkRecommend(LikeVO recommend) throws Exception;
	public void insertBoardRecommend(LikeVO recommend);
	public void deleteBoardRecommend(LikeVO recommend);
}
