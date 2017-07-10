package kr.co.torrent.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 어떤 경로의 어떤 파일을 어떤 이름으로 다운로드 할지
		// 사용자가 파라미터로 넘겨야 한다.
		
		ServletContext context = request.getServletContext();
		// 서버에 저장되는 기본 경로
		System.out.println("context:"+context);
		String uploadPath = context.getRealPath("/upload");
		System.out.println("uploadPath:"+uploadPath);
		// path : 파일 저장된 경로
		String path = request.getParameter("path");
		// sName : 경로에 실제 존재하는 파일명
		String sName = request.getParameter("sName");
		
		// 파일을 읽기 위한 파일 객체 생성
		File f = new File(uploadPath + path, sName);
		
		// dName : 사용자 컴퓨터에 저장할 파일명
		String dName = request.getParameter("dName");
		// 다운로드할 이름을 주지 않은 경우 사용자 브라우져에 이미지 출력	
		if (dName == null) {
			response.setHeader("Content-Type", "image/jpg");
		}
		// 다운로드 
		else {
			response.setHeader(
					"Content-Type", 
					"application/octet-stream"
			);
			// 한글이름으로 다운로드 처리
			dName = 
				new String(dName.getBytes("utf-8"), "8859_1");
			response.setHeader(
					"Content-Disposition", 
					"attachment;filename=" + dName
			);
			response.setHeader(
					"Content-Transfer-Encoding", 
					"binary"
			);
			response.setHeader(
					"Content-Length",
					String.valueOf(f.length())
			);
		}
		
		// 특정 위치의 파일을 읽어서 사용자 브라우져로 출력
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		OutputStream out = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		while (true) {
			int ch = bis.read();
			if (ch == -1) break;
			
			bos.write(ch);
		}
		bis.close();
		fis.close();
		
		bos.close();
		out.close();
	
	}
}























