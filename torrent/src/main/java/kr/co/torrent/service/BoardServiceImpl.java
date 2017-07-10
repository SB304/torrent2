package kr.co.torrent.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import kr.co.torrent.mapper.BoardMapper1;
import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageResultVO;
import kr.co.torrent.vo.PageVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper1 mapper;

	@Override
	public Map<String, Object> list(PageVO page) {
		List<BoardVO> board = mapper.selectBoard(page);
		int count = mapper.selectBoardCount(page);
		int pageNo = page.getPageNo();
		int begin = page.getBegin();
		int end = page.getEnd();
		Map<String, Object> result = new HashMap<>();
		result.put("board", board);
		result.put("pageResult", new PageResultVO(pageNo, count));
		result.put("count", count);
		result.put("begin", begin);
		result.put("end", end);
		return result;
	}
	
	@Override
	public Map<String, Object> detail(int bno) {
		BoardVO  board = mapper.selectBoardByNo(bno);
		FileVO file = mapper.selectFileByNo(bno);
		
		Map<String, Object> result = new HashMap<>();
		result.put("board", board);
		result.put("file", file);
		return result;
	}
	
	@Override
	public void delete(int bno) {
		mapper.deleteBoard(bno);
	}

	

	@Override
	public int update(BoardVO board) {
		return mapper.updateBoard(board);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int doLike(LikeVO like){
		LikeVO result = mapper.checkLike(like);
		if(result != null) {
			mapper.deleteLike(like);
		}
		else {
			mapper.insertLike(like);
		}
		return mapper.selectLikeCount(like.getBno());
	}

	@Override
	public int likeCount(int bno) {
		return mapper.selectLikeCount(bno);
	}

	@Transactional
	@Override
	public void insert(BoardVO board, FileVO file) {
		mapper.insertBoard(board);
		int bno = mapper.selectBno();
		if(file != null){
			System.out.println("======");
			System.out.println(bno);
			file.setBno(bno);
			mapper.insertFile(file);
		}
	}

}
