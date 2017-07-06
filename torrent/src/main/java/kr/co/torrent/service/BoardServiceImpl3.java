package kr.co.torrent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.torrent.mapper.BoardMapper3;
import kr.co.torrent.vo.ReplyVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.BoardVO;

@Service("BoardService3")
public class BoardServiceImpl3 implements BoardService3 {

	@Autowired
	private BoardMapper3 mapper;
	
	////////// 보드///////////////////
	public BoardServiceImpl3() {}

	@Override
	public BoardVO updateForm(int no) throws Exception {
		return mapper.selectBoardByNo(no);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(BoardVO board) throws Exception {
		mapper.updateBoard(board);
	}

	@Override
	public List<BoardVO> select(int genre) throws Exception {
		return mapper.selectBoard(genre);
	}
	@Override
	public Map<String, Object> detail(int no) throws Exception {
		BoardVO board = mapper.selectBoardByNo(no);
		FileVO file = mapper.selectFileByNo(no);
		Map<String, Object> map = new HashMap<>();
		map.put("file", file);
		map.put("board", board);
		return map;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insert(BoardVO board, FileVO file) throws Exception {
		if (file != null) {
			mapper.insertFile(file);
		}
		mapper.insertBoard(board);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(int no) throws Exception {
		mapper.deleteBoard(no);
	}

	////////////// 댓글///////////////////////////
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteComment(int no) throws Exception {
		mapper.deleteReply(no);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateComment(ReplyVO comment) throws Exception {
		mapper.updateReply(comment);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertComment(ReplyVO comment) throws Exception {
		mapper.insertReply(comment);
//		session.commit();
	}

	@Override
	public List<ReplyVO> selectCommentByNo(int no) throws Exception {
		return mapper.selectBoardCommentByNo(no);
	}

	//////////// 추천수/////////////////////////////////
	@Override
	public LikeVO checkRecommend(LikeVO recommend) throws Exception {
		return mapper.checkLike(recommend);
	}

	@Override
	public int countRecommend(int no) throws Exception {
		return mapper.countLike(no);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteRecommend(LikeVO recommend) throws Exception {
		mapper.deleteLike(recommend);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
		public void insertRecommend(LikeVO recommend) throws Exception {
			mapper.insertLike(recommend);
		}
}