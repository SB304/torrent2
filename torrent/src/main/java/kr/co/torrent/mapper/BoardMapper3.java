package kr.co.torrent.mapper;

import java.util.List;

import kr.co.torrent.vo.ReplyVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.BoardVO;

public interface BoardMapper3 {
	public int insertBoard(BoardVO board) throws Exception;
	public int updateBoard(BoardVO board) throws Exception;
	public int deleteBoard(int no) throws Exception;
	public List<BoardVO> selectBoard(int genre) throws Exception;
	public BoardVO selectBoardByNo(int no) throws Exception;

	// 파일 작업
	public void insertFile(FileVO fileVO) throws Exception;
	public FileVO selectFileByNo(int fileNo) throws Exception;
	
	// 댓글 작업
	public List<ReplyVO> selectReplyByNo(int no) throws Exception;
	public void insertReply(ReplyVO comment) throws Exception;
	public void deleteReply(int commentNo) throws Exception;
	public void updateReply(ReplyVO comment) throws Exception;
	//추천수 작업
	public int countLike(int boardNo) throws Exception;
	public LikeVO checkLike(LikeVO recommend) throws Exception;
	public void insertLike(LikeVO recommend);
	public void deleteLike(LikeVO recommend);
}
