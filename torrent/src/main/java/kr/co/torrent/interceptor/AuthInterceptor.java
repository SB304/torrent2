package kr.co.torrent.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthInterceptor");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect("auth");
			return false;
		}
		return true;
	}

}
