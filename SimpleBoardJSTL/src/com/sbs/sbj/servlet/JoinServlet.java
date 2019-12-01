package com.sbs.sbj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.sbj.dao.UserDAO;
import com.sbs.sbj.vo.UserVO;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/join.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String nm = request.getParameter("nm");
		System.out.println("uid : " + uid);
		System.out.println("upw : " + upw);
		System.out.println("nm : " + nm);
		
		UserVO param = new UserVO();
		param.setUid(uid);
		param.setUpw(upw);
		param.setNm(nm);
		
		int result = UserDAO.join(param);
		
		if(result == 1) {
			response.sendRedirect("login?joinSuccess=1");
		} else {
			String msg = null;
			switch(result) {
			case 0:
				msg = "에러 발생!";
				break;
			case 2:
				msg = "중복아이디가 존재합니다.";
				break;
			case 3:
				msg = "값이 너무 길어 저장할 수 없습니다.";
				break;
			}
			request.setAttribute("uid", uid);
			request.setAttribute("nm",  nm);
			request.setAttribute("msg",  msg);
			doGet(request, response);
		}
	}

}
