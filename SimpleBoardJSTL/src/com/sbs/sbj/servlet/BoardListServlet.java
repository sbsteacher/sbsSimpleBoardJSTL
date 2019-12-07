package com.sbs.sbj.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.sbj.Utils;
import com.sbs.sbj.dao.BoardDAO;
import com.sbs.sbj.vo.BoardVO;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		
		request.setAttribute("title", "리스트");
		request.setAttribute("target", "list");
		String page = request.getParameter("page");
		int intPage = 1;
		if(page != null) {		
			intPage = Utils.parseStringToInt(page, 1);
		}
		hs.setAttribute("selectedPage", intPage);		
		
		BoardVO param = new BoardVO();
		param.setPage(intPage);
		
		List<BoardVO> list = BoardDAO.getBoardList(param);
		request.setAttribute("list", list);
		
		int pagingCnt = BoardDAO.getPagingCnt();
		request.setAttribute("pagingCnt",  pagingCnt);
		
		request.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
