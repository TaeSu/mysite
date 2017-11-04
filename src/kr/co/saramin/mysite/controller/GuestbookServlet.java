package kr.co.saramin.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.saramin.mysite.dao.GuestbookDao;
import kr.co.saramin.mysite.vo.GuestbookVo;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("a");
		
		if ("add".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			List<GuestbookVo> list = dao.getList();
			request.setAttribute("list", list);
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/guestbook");
		} else if ("delete".equals(actionName)) {
			String sNo = request.getParameter("no");
			Long no = Long.parseLong(sNo);
			String password = request.getParameter("password");
			
			GuestbookDao dao = new GuestbookDao();
			dao.delete(no, password);
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/guestbook");
		} else if ("deleteform".equals(actionName)) {
			String no = request.getParameter("no");
			request.setAttribute("no", no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);
		} else {
			/* default action(list) */
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
