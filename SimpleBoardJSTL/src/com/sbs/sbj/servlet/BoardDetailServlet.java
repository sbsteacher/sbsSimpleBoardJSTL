package com.sbs.sbj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.sbj.Utils;
import com.sbs.sbj.dao.BoardDAO;
import com.sbs.sbj.vo.BoardVO;

@WebServlet("/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = Utils.parseStringToInt(request.getParameter("i_board"), 0);
		
		if(i_board != 0) {
			BoardVO param = new BoardVO();
			param.setI_board(i_board);
			request.setAttribute("data", BoardDAO.getBoardDetail(param));
		}
		
		System.out.println("i_board : " + i_board);
		
		request.setAttribute("target", "detail");
		request.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
