package portit.controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dto.Profile;

@WebServlet(urlPatterns="/register")
public class ProfileController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		      
		String cmd = req.getParameter("cmd");
		String url ="";
		//String mem_id = req.getSession().getAttribute("mem_id").toString();
		/*
		 * dto 에 변수 넣기
		 * dao로 insert 처리 Dto dto = dao.addprofile(dto);
		 * dto를 request 에 속성으로 지정 request.setAttribute("dto", dto)
		 * 
		 * */
		
		//화면에서
		/* 제일 위에
		 * <% 
		 *   Dto dto = request.getAttribute("dto");
		 * %>
		 * value="<%=dto.get??? %>"
		 * */
		if(cmd.equals("REGISTER")){
			//return  new RegisterCommand();
			url="/myProf.jsp";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
		
	}

	/*public Profile profile(HttpServletRequest req){
		String prof_nick = req.getParameter("prof_nick");*/
	}

