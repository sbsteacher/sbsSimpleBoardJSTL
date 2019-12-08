package com.sbs.sbj.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.sbj.dao.BoardDAO;
import com.sbs.sbj.vo.BoardVO;
import com.sbs.sbj.vo.UserVO;

@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "글쓰기");
		request.setAttribute("target", "write");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-- doPost --");	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//int intContent = Integer.parseInt(content);
		
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		//int, char, float, byte, short, boolean, double 
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		
		//누가 작성하는 글인지 uid값을 세팅!!
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		vo.setUid(loginUser.getUid());
		int result = BoardDAO.writeBoard(vo);
		
		if(result == 1) {
			response.sendRedirect("list");
		} else {
			request.setAttribute("msg", "등록을 할 수 없습니다.");
			doGet(request, response);
		}
	}

}
