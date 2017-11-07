package kr.co.saramin.mysite.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.saramin.mvc.action.Action;
import kr.co.saramin.mysite.dao.UserDao;
import kr.co.saramin.mysite.vo.UserVo;

public class AuthAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email, password); 
		
		// 인증 에러
		if (vo == null) {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 인증 처리 (Session 처리)
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		response.sendRedirect("/mysite");
	}
}
