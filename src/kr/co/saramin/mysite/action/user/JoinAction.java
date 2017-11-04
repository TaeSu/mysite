package kr.co.saramin.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.saramin.mvc.action.Action;
import kr.co.saramin.mysite.dao.UserDao;
import kr.co.saramin.mysite.vo.UserVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		UserDao dao = new UserDao();
		if (dao.insert(vo)) {
			response.sendRedirect("/mysite/user?a=joinsuccess");
		} else {
			response.sendRedirect("/mysite/user?a=joinfail");
		}
	}

}
