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
import com.sbs.sbj.vo.CommentVO;

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

	//댓글처리(등록, 삭제)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_comment = Utils.parseStringToInt(request.getParameter("i_comment"), 0);
		int i_board = Utils.parseStringToInt(request.getParameter("i_board"), 0);
		String cmt = request.getParameter("cmt");
	
		if(i_board == 0) { //리스트로 이동!!
			response.sendRedirect("list");
			return;
		}
		
		CommentVO param = new CommentVO();				
		param.setI_board(i_board);
						
		if(i_comment == 0) { //댓글 등록
			param.setCmt(cmt);	
			int result = BoardDAO.regComment(param);
			
			if(result == 1) {
				response.sendRedirect("detail?i_board="+i_board);
			} else {
				request.setAttribute("msg", "댓글을 등록할 수 없습니다.");
				doGet(request, response);
			}
			
		} else { //댓글 삭제
			param.setI_comment(i_comment);	
			
			int result = BoardDAO.delComment(param);
			if(result == 1) {
				response.sendRedirect("detail?i_board="+i_board);
			} else {
				request.setAttribute("msg", "댓글을 등록할 수 없습니다.");
				doGet(request, response);
			}
		}
		
	}

}















