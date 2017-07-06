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
@RequestMapping("/board") 
public class BoardController3 {
	@Autowired
	private BoardService3 boardService3;
	@Autowired
	ServletContext servletContext;
	public BoardController3() {
		boardService3 = new BoardServiceImpl3(); //지금은 직접주입하지만 스프링에선 오토와이어드...
	}
	/////////////////////댓글처리 컨트롤러 : 모두 ajax이므로 @ResponseBody 이용 /////////////////////////
	@RequestMapping("/commentDelete.do")
	public List<ReplyVO> commentDeleteAjax(ReplyVO comment) throws Exception {
		boardService3.deleteComment(comment.getCommentNo());
		return boardService3.selectCommentByNo(comment.getNo());
	}
	@RequestMapping("/board/commentList.do")
	public List<ReplyVO> CommentListControllerAjax(int no) throws Exception {
		return boardService3.selectCommentByNo(no);
	}
	@RequestMapping("/board/commentRegist.do")
	public List<ReplyVO> CommentRegistControllerAjax(ReplyVO comment) throws Exception {
		boardService3.insertComment(comment);
		return boardService3.selectCommentByNo(comment.getNo());
	}
	@RequestMapping("/board/commentUpdate.do")
	public List<ReplyVO> CommentUpdateAjax(ReplyVO comment) throws Exception {
		boardService3.updateComment(comment);
		return boardService3.selectCommentByNo(comment.getNo());

	}

	//////////////////////////보드관련 컨트롤러 처리 ////////////////////////////////////
	@RequestMapping("/board/delete.do")
	public void delete(int no) throws Exception {
		boardService3.delete(no);
	}

	@RequestMapping("/board/detail.do")
	public ModelAndView detail(int no) throws Exception {
		Map<String,Object> result = boardService3.detail(no);
		ModelAndView mav = new ModelAndView("/board/detail");
		mav.addObject("board", result.get("board"));
		mav.addObject("file", result.get("file"));

		return mav;
	}

	@RequestMapping("/board/list.do")
	public ModelAndView list(
			@RequestParam(value="pageNo", defaultValue="1")int pageNo) 
					throws Exception {
		System.out.println("pageNo = "+pageNo);
		List<BoardVO> list = boardService3.select();
		ModelAndView mav = new ModelAndView("/board/list");
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("/board/update.do")
	public void update(BoardVO board) throws Exception {
		boardService3.update(board);
	}

	@RequestMapping("/board/updateForm.do")
	public ModelAndView updateForm(int no) throws Exception {
		BoardVO board = boardService3.updateForm(no);
		
		ModelAndView mav = new ModelAndView("/board/updateForm");
		mav.addObject("board", board);
		return mav;
	}

	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
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
				boardFile.setNo(boardVO.getBno());
				boardFile.setOriName(oriFileName);
				boardFile.setSystemName(saveFileName);
				boardFile.setFilePath(datePath);
				boardFile.setFileSize(fileSize);
			}
		} 
		boardService3.insert(boardVO, boardFile);
		System.out.println("등록된 게시물 번호 : " + boardVO.getBno());
		
	}

	@RequestMapping("/board/writeForm.do")
	public void writeForm() throws Exception {
	}

	////////////추천수 처리///////////////////////
	@RequestMapping("/board/checkRecommend.do")
	public String checkRecommendAjax(LikeVO recommend) throws Exception {
		LikeVO check = boardService3.checkRecommend(recommend);
		String result = "추천";
		if (check != null) {
			System.out.println("이미추천했지롱");
			result = "추천취소";
		}
		return result;
	}
	@RequestMapping("/board/recommendCount.do")
	public int recommendCountAjax(int boardNo) throws Exception {
		return boardService3.countRecommend(boardNo);
	}
	@RequestMapping("/board/deleteRecommend.do")
	public int deleteRecommendAjax(LikeVO recommend) throws Exception {
		boardService3.deleteRecommend(recommend);
		return boardService3.countRecommend(recommend.getBoardNo());

	}
	@RequestMapping("/board/insertRecommend.do")
	public int insertRecommendAjax(LikeVO recommend) throws Exception {
		boardService3.insertRecommend(recommend);
		return boardService3.countRecommend(recommend.getBoardNo()); 
	}


}
