package kr.co.torrent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.torrent.mapper.BoardMapper3;
import kr.co.torrent.repository.vo.PageResultVO;
import kr.co.torrent.repository.vo.PageVO;
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
	public Map<String, Object> select(PageVO page) throws Exception {
		
		System.out.println("page begin"+page.getBegin());
		System.out.println("page end"+page.getEnd());
		System.out.println("page genre"+page.getGenre());
		
		List<BoardVO> list = mapper.selectBoard(page);
		int count = mapper.selectBoardCount(page);
		int pageNo = page.getPageNo();
		
		//두개를 묶기위해 VO나 Map을 이용하는게 적절..
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageResult", new PageResultVO(pageNo, count));
		return map;
	
	}
	@Override
	public Map<String, Object> detail(int bno) throws Exception {
		BoardVO board = mapper.selectBoardByNo(bno);
		FileVO file = mapper.selectFileByNo(bno);
		Map<String, Object> map = new HashMap<>();
		map.put("file", file);
		map.put("board", board);
		return map;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insert(BoardVO board, FileVO file) throws Exception {
		int bno = mapper.insertBoard(board);
		if (file != null) {
			System.out.println("새로작성한 글번호 : "+board.getBno());
			file.setBno(board.getBno());
			mapper.insertFile(file);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(int no) throws Exception {
		mapper.deleteBoard(no);
	}

	////////////// 댓글///////////////////////////
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteComment(int rno) throws Exception {
		mapper.deleteReply(rno);
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
	public List<ReplyVO> selectReplyByNo(int bno) throws Exception {
		return mapper.selectReplyByNo(bno);
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