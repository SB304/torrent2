package kr.co.torrent.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageVO;

public interface BoardMapper1 {
	public int selectBoardCount(PageVO page);
	public List<BoardVO> selectBoard(PageVO page);
	public List<BoardVO> selectBoardByNo(int bno);
	public void deleteBoard(int bno);
	public Map<String, Object> insertBoard(@Param("board") BoardVO board, @Param("file") FileVO file);
	public int updateBoard(BoardVO board);
	
	public LikeVO checkLike(LikeVO like);
	public void insertLike(LikeVO like);
	public void deleteLike(LikeVO like);
	public int selectLikeCount(int no);
	public int doLike(LikeVO like);
}
