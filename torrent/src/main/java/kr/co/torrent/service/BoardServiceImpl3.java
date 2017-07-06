package kr.co.torrent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.BoardMapper3;
import kr.co.torrent.vo.BoardCommentVO;
import kr.co.torrent.vo.BoardFileVO;
import kr.co.torrent.vo.BoardRecommendVO;
import kr.co.torrent.vo.BoardVO;

@Service
public class BoardServiceImpl3 implements BoardService3 {

	@Autowired
	private BoardMapper3 mapper;
	////////// 보드///////////////////
	public BoardServiceImpl3() {
		
		}

	@Override
	public BoardVO updateForm(int no) throws Exception {
		return mapper.selectBoardByNo(no);
	}

	@Override
	public void update(BoardVO board) throws Exception {
		mapper.updateBoard(board);
//		session.commit();
	}

	@Override
	public List<BoardVO> select() throws Exception {
		return mapper.selectBoard();
	}

	@Override
	public Map<String, Object> detail(int no) throws Exception {
		BoardVO board = mapper.selectBoardByNo(no);
		BoardFileVO file = mapper.selectBoardFileByNo(no);
		Map<String, Object> map = new HashMap<>();
		map.put("file", file);
		map.put("board", board);

		return map;
	}

	@Override
	public void insert(BoardVO board, BoardFileVO file) throws Exception {
		if (file != null) {
			mapper.insertBoardFile(file);
		}
		mapper.insertBoard(board);
//		session.commit();

	}

	@Override
	public void delete(int no) throws Exception {
		mapper.deleteBoard(no);
		// 댓글이랑 추천수 테이블도 지워야핱텐데..?
//		session.commit();
	}

	////////////// 댓글///////////////////////////
	@Override
	public void deleteComment(int no) throws Exception {
		mapper.deleteBoardComment(no);
//		session.commit();
	}

	@Override
	public void updateComment(BoardCommentVO comment) throws Exception {
		mapper.updateBoardComment(comment);
//		session.commit();

	}

	@Override
	public void insertComment(BoardCommentVO comment) throws Exception {
		mapper.insertBoardComment(comment);
//		session.commit();
	}

	@Override
	public List<BoardCommentVO> selectCommentByNo(int no) throws Exception {
		return mapper.selectBoardCommentByNo(no);
	}

	//////////// 추천수/////////////////////////////////
	@Override
	public BoardRecommendVO checkRecommend(BoardRecommendVO recommend) throws Exception {
		return mapper.checkRecommend(recommend);
	}

	@Override
	public int countRecommend(int no) throws Exception {
		return mapper.countBoardRecommend(no);
	}

	@Override
	public void deleteRecommend(BoardRecommendVO recommend) throws Exception {
		mapper.deleteBoardRecommend(recommend);
//		session.commit();
	}

	@Override
		public void insertRecommend(BoardRecommendVO recommend) throws Exception {
			mapper.insertBoardRecommend(recommend);
//			session.commit();
		}
}