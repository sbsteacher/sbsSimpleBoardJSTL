package com.sbs.sbj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.sbj.Utils;
import com.sbs.sbj.dao.BoardDAO;
import com.sbs.sbj.vo.CommentVO;

@WebServlet("/del")
public class BoardDelServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = Utils.parseStringToInt(request.getParameter("i_board"), 0);
		
		if(i_board == 0) {
			response.sendRedirect("list");
			return;
		}
		
		//댓글 먼저 삭제
		CommentVO param = new CommentVO();
		param.setI_board(i_board);
		BoardDAO.delComment(param);

		//글 삭제
		BoardDAO.delBoard(i_board);
		
		response.sendRedirect("list");
	}
}
