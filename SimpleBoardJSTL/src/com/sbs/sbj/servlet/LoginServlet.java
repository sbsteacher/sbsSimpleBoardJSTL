package com.sbs.sbj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.sbj.dao.UserDAO;
import com.sbs.sbj.vo.UserVO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp")
		.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		System.out.println("uid : " + uid);
		System.out.println("upw : " + upw);
		
		UserVO param = new UserVO();
		param.setUid(uid);
		param.setUpw(upw);
		
		int result = UserDAO.login(param);
		
		if(result == 1) {
			
		} else {
			String msg = null;
			switch(result) {
			case 0:
				msg = "에러 발생!!!";
				break;
			case 2:
				msg = "아이디를 다시 확인해 주세요.";
				break;
			case 3:
				msg = "비밀번호가 맞지 않습니다.";
				break;
			}
			request.setAttribute("uid", uid);
			request.setAttribute("msg", msg);
			doGet(request, response);
		}
		
		
	}

}
