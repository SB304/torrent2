package kr.co.torrent.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.torrent.service.BoardService3;
import kr.co.torrent.service.BoardServiceImpl3;
import kr.co.torrent.vo.ReplyVO;
import kr.co.torrent.vo.FileVO;
import kr.co.torrent.vo.LikeVO;
import kr.co.torrent.vo.BoardVO;

//한상미 컨트롤러

@RestController 
@RequestMapping("/views/board") 
public class BoardController3 {
	@Autowired
	private BoardService3 boardService3;
	@Autowired
	ServletContext servletContext;
	public BoardController3() {
		boardService3 = new BoardServiceImpl3(); //지금은 직접주입하지만 스프링에선 오토와이어드...
	}
	/////////////////////댓글처리 컨트롤러 : 모두 ajax이므로 @ResponseBody 이용 /////////////////////////
	@RequestMapping("/commentDelete.json")
	public List<ReplyVO> commentDeleteAjax(ReplyVO comment) throws Exception {
		boardService3.deleteComment(comment.getRno());
		return boardService3.selectCommentByNo(comment.getBno());
	}
	@RequestMapping("/commentList.json")
	public List<ReplyVO> CommentListControllerAjax(int no) throws Exception {
		return boardService3.selectCommentByNo(no);
	}
	@RequestMapping("/commentRegist.json")
	public List<ReplyVO> CommentRegistControllerAjax(ReplyVO comment) throws Exception {
		boardService3.insertComment(comment);
		return boardService3.selectCommentByNo(comment.getBno());
	}
	@RequestMapping("/commentUpdate.json")
	public List<ReplyVO> CommentUpdateAjax(ReplyVO comment) throws Exception {
		boardService3.updateComment(comment);
		return boardService3.selectCommentByNo(comment.getBno());

	}

	//////////////////////////보드관련 컨트롤러 처리 ////////////////////////////////////
	@RequestMapping("/delete.json")
	public void delete(int bno) throws Exception {
		boardService3.delete(bno);
	}

	@RequestMapping("/detail.json")
	public Map<String,Object> detail(int bno) throws Exception {
		Map<String,Object> result = boardService3.detail(bno);
		return result;
	}

	@RequestMapping("/list.json")
	public List<BoardVO> list(
			@RequestParam(value="pageNo", defaultValue="1")int pageNo,int genre) 
					throws Exception {
		System.out.println("pageNo = " + pageNo);
		List<BoardVO> list = boardService3.select(genre);
		return list;
	}

	@RequestMapping("/update.json")
	public void update(BoardVO board) throws Exception {
		boardService3.update(board);
	}

	@RequestMapping("/updateForm.json")
	public BoardVO updateForm(int bno) throws Exception {
		BoardVO board = boardService3.updateForm(bno);
		return board;
	}

	@RequestMapping(value="/write.json", method=RequestMethod.POST)
	public void write(MultipartHttpServletRequest mRequest, BoardVO boardVO) throws Exception {
		
/*		//현재 프로젝트와 관련된 ServletContext 객체를 자동주입함 : 기존에는 HttpRequest객체에서 꺼냈음..
		ServletContext context = request.getServletContext();*/
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
				mFile.transferTo(new File(uploadPath + "/" + saveFileName));
				
				boardFile = new FileVO();
				boardFile.setBno(boardVO.getBno());
				boardFile.setOriName(oriFileName);
				boardFile.setSysName(saveFileName);
				boardFile.setPath(datePath);
				boardFile.setSize(fileSize);
			}
		} 
		boardService3.insert(boardVO, boardFile);
		System.out.println("등록된 게시물 번호 : " + boardVO.getBno());
		
	}

	@RequestMapping("/writeForm.json")
	public void writeForm() throws Exception {
	}

	////////////추천수 처리///////////////////////
	@RequestMapping("/checkLike.json")
	public String checkRecommendAjax(LikeVO recommend) throws Exception {
		LikeVO check = boardService3.checkRecommend(recommend);
		String result = "추천";
		if (check != null) {
			System.out.println("이미추천했지롱");
			result = "추천취소";
		}
		return result;
	}
	@RequestMapping("/LikeCount.json")
	public int recommendCountAjax(int boardNo) throws Exception {
		return boardService3.countRecommend(boardNo);
	}
	@RequestMapping("/deleteLike.json")
	public int deleteRecommendAjax(LikeVO recommend) throws Exception {
		boardService3.deleteRecommend(recommend);
		return boardService3.countRecommend(recommend.getBno());

	}
	@RequestMapping("/insertLike.json")
	public int insertRecommendAjax(LikeVO recommend) throws Exception {
		boardService3.insertRecommend(recommend);
		return boardService3.countRecommend(recommend.getBno()); 
	}
}
