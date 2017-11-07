package kr.co.saramin.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.saramin.mvc.action.Action;
import kr.co.saramin.mysite.dao.UserDao;
import kr.co.saramin.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 접근제어 */
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect("/mysite");
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect("/mysite");
			return;
		}
		
		Long no = authUser.getNo();
		
		UserDao dao = new UserDao();
//		UserVo vo = dao.get(no);
	}
}