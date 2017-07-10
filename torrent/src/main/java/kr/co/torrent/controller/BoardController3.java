package kr.co.torrent.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.torrent.repository.vo.PageVO;
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
		return boardService3.selectReplyByNo(comment.getBno());
	}
	@RequestMapping("/commentList.json")
	public List<ReplyVO> CommentListControllerAjax(int bno) throws Exception {
		System.out.println("?? :"+bno);
		return boardService3.selectReplyByNo(bno);
	}
	@RequestMapping("/commentRegist.json")
	public List<ReplyVO> CommentRegistControllerAjax(ReplyVO comment) throws Exception {
		boardService3.insertComment(comment);
		return boardService3.selectReplyByNo(comment.getBno());
	}
	@RequestMapping("/commentUpdate.json")
	public List<ReplyVO> CommentUpdateAjax(ReplyVO comment) throws Exception {
		boardService3.updateComment(comment);
		return boardService3.selectReplyByNo(comment.getBno());

	}

	//////////////////////////보드관련 컨트롤러 처리 ////////////////////////////////////
	@RequestMapping("/download.json")
	public void download(int bno) throws Exception {
		boardService3.delete(bno);
	}
	
	
	@RequestMapping("/delete.json")
	public void delete(int bno) throws Exception {
		boardService3.delete(bno);
	}

	@RequestMapping("/detail.json")
	public Map<String,Object> detail(int bno) throws Exception {
		System.out.println(bno);
		Map<String,Object> result = boardService3.detail(bno);
		boardService3.viewCntUP(bno);
		return result;
	}

	@RequestMapping("/list.json")
	public Map<String, Object> list(PageVO page) 
					throws Exception {
		System.out.println("pageNo: "+ page.getPageNo());
		System.out.println("genre :"+page.getGenre());
		
		
		Map<String, Object> map = boardService3.select(page);
		return map;
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
	
	@RequestMapping(value="/readyImg.json", method=RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest mRequest) throws Exception {
		String uploadDir = servletContext.getRealPath("/tempImgUpload");
		System.out.println(uploadDir);
		File f = new File(uploadDir);
		if (!f.exists()) {
			f.mkdirs();
		}
		//attachFile1, 2... 반환을 Iterator로 반환함(기존 Eneu???)
		Iterator<String> iter = mRequest.getFileNames();
		String sendPath = "";
		while(iter.hasNext()) {
			String formFileName = iter.next();
			// 폼에서 파일을 선택하지 않아도 객체 생성됨 : cos는 Null로 넘어왔는데, spring은 null이 아님..
			//MultipartFile 이름에해당하는 파일 정보를 갖는 객체
			MultipartFile mFile = mRequest.getFile(formFileName);
			// 원본 파일명
			String oriFileName = mFile.getOriginalFilename();
			System.out.println("원본 파일명 : " + oriFileName);
			
			//원본화일명이 null이 아니라면 = 파일을 선택했다면!
			if(oriFileName != null && !oriFileName.equals("")) {
			System.out.println("여긴들어오냐,,,");
				// 확장자 처리
				String ext = "";
				// 뒤쪽에 있는 . 의 위치 
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					// 파일명에서 확장자명(.포함)을 추출
					ext = oriFileName.substring(index);
					System.out.println("여긴들어오냐,,,222");

				}
				/*if(!(ext.equalsIgnoreCase("png")||ext.equalsIgnoreCase("jpg")||ext.equalsIgnoreCase("jpeg"))){
					return "notIMG";
				}*/
				// 고유한 파일명 만들기	
				String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
				System.out.println("저장할 파일명 : " + saveFileName);
				
				//cos.jar는 객체를 생성한 순간 서버에 저장하지만, spring은 사용자 action : transferTo를 통해 저장됨!
				// 임시저장된 파일을 원하는 경로에 저장
				mFile.transferTo(new File(uploadDir + "/" + saveFileName));
				sendPath=  "http://localhost:8000/torrent/tempImgUpload/"+saveFileName;
				System.out.println("sendPath : " +sendPath);
			} 
		} 
		return sendPath;
	}
	
	@RequestMapping(value="/write.json", method=RequestMethod.POST)
	public void write(MultipartHttpServletRequest mRequest, BoardVO boardVO, HttpSession session) throws Exception {
		
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
				boardFile.setOriName(oriFileName);
				boardFile.setSysName(saveFileName);
				boardFile.setPath(datePath);
				boardFile.setSize(fileSize);
			}
		} 
		String id = (String)session.getAttribute("user");
		boardVO.setId(id);
		boardVO.setGenre(3);
		boardVO.setTitle(mRequest.getParameter("title"));
		boardVO.setContent(mRequest.getParameter("content"));
		
		System.out.println("board id :" + id);
		System.out.println("board title :" + boardVO.getTitle());
		System.out.println("board content :" + boardVO.getContent());
		boardService3.insert(boardVO, boardFile);
		System.out.println("등록된 게시물 번호 : " + boardVO.getBno());
		
	}

	@RequestMapping("/writeForm.json")
	public void writeForm() throws Exception {
	}
	
	

	////////////추천수 처리///////////////////////
	@RequestMapping("/checkLike.json")
	public String checkRecommendAjax(LikeVO recommend, HttpSession session) throws Exception {
		String id = (String)session.getAttribute("user");
		recommend.setId(id);
		LikeVO check = boardService3.checkRecommend(recommend);
		String result = "/torrent/resources/images/unlike.png";
		if (check != null) {
			System.out.println("이미추천했지롱");
			result = "/torrent/resources/images/like.png";
		}
		return result;
	}
	@RequestMapping("/likeCount.json")
	public int recommendCountAjax(int bno) throws Exception {
		return boardService3.countRecommend(bno);
	}
	@RequestMapping("/deleteLike.json")
	public int deleteRecommendAjax(LikeVO recommend , HttpSession session) throws Exception {
		String id = (String)session.getAttribute("user");
		recommend.setId(id);
		boardService3.deleteRecommend(recommend);
		return boardService3.countRecommend(recommend.getBno());

	}
	@RequestMapping("/insertLike.json")
	public int insertRecommendAjax(LikeVO recommend,HttpSession session) throws Exception {
		String id = (String)session.getAttribute("user");
		recommend.setId(id);
		boardService3.insertRecommend(recommend);
		return boardService3.countRecommend(recommend.getBno()); 
	}
}
