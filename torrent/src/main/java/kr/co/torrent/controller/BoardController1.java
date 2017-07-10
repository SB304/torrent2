package kr.co.torrent.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.torrent.service.BoardService;
import kr.co.torrent.vo.BoardVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.PageVO;

@RestController
public class BoardController1 {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	ServletContext servletContext;

	// 리스트
	@RequestMapping("/board1list.json")
	public Map<String, Object> list(PageVO page) {
		return boardService.list(page);
	}

	// 상세보기
	@RequestMapping("/board1detail.json")
	public List<BoardVO> detail(int bno) {
		return boardService.detail(bno);
	}

	// 삭제
	@RequestMapping("/board1delete.json")
	public void delete(int bno) {
		boardService.delete(bno);
	}

	// 수정
	@RequestMapping("/board1update.json")
	@Transactional(rollbackFor=Exception.class)
	public void Update(BoardVO board) {
		boardService.update(board);
		
	}
	
	// 등록
	@RequestMapping("/board1write.json")
	public void write(MultipartHttpServletRequest mRequest, BoardVO boardVO, HttpSession session) {
		String uploadPath = servletContext.getRealPath("/upload");
		// upload 하위에 모듈별 날짜 형태의 디렉토리 생성후 저장
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String datePath = sdf.format(new Date());
		uploadPath += datePath;
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		Iterator<String> iter = mRequest.getFileNames();
		FileVO boardFile = null;
		while (iter.hasNext()) {
			String formFileName = iter.next();
			MultipartFile mFile = mRequest.getFile(formFileName);
			String oriFileName = mFile.getOriginalFilename();
			System.out.println("원본 파일명 : " + oriFileName);
			if (oriFileName != null && !oriFileName.equals("")) {
				String ext = "";
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					ext = oriFileName.substring(index);
				}

				long fileSize = mFile.getSize();
				System.out.println("파일 사이즈 : " + fileSize);
				String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
				System.out.println("저장할 파일명 : " + saveFileName);
				try {
					mFile.transferTo(new File(uploadPath + "/" + saveFileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				boardFile = new FileVO();
				boardFile.setOriName(oriFileName);
				boardFile.setSysName(saveFileName);
				boardFile.setPath(datePath);
				boardFile.setSize(fileSize);
			}
		} 
		String id = (String)session.getAttribute("user");
		boardVO.setId(id);
		boardVO.setGenre(1);
		boardVO.setTitle(mRequest.getParameter("title"));
		boardVO.setContent(mRequest.getParameter("content"));
		
		boardService.insert(boardVO, boardFile);
	}
	
	// 추천
	@RequestMapping("/board1like.json")
	public int boardLike(LikeVO like){
		return boardService.doLike(like);
	}
	
	// 추천 수
	@RequestMapping("/board1likecount.json")
	public int boardLikeCount(int bno){
		return boardService.likeCount(bno);
	}
	
}
