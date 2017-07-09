package kr.co.torrent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torrent.mapper.BoardMapper1;
import kr.co.torrent.vo.BoardVO;
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
		System.out.println(pageNo);
		Map<String, Object> result = new HashMap<>();
		result.put("board", board);
		result.put("pageResult", new PageResultVO(pageNo, count));
		result.put("count", count);
		return result;
	}
	
	@Override
	public List<BoardVO> detail(int bno) {
		return mapper.selectBoardByNo(bno);
	}
	
	@Override
	public int delete(int bno) {
		return mapper.deleteBoard(bno);
	}

	@Override
	public int insert(BoardVO board) {
		return mapper.insertBoard(board);
	}

	@Override
	public int update(BoardVO board) {
		return mapper.updateBoard(board);
	}

}
