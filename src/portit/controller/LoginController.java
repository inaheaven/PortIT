package portit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.Member_ViewDao;
import portit.model.dao.Portfolio_ViewDao;
import portit.model.dao.Proj_viewDao;
import portit.model.dao.Timeline_ViewDao;
/**
 * 
 * @author isyi
 * 로그인 성공시, main화면으로 이동하기 위한 컨트롤러
 *
 */
@WebServlet(urlPatterns="/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String cmd = req.getParameter("cmd");
		String url = null;
		
		//dao 호출
		Portfolio_ViewDao portfolio = new Portfolio_ViewDao();
		Member_ViewDao member = new Member_ViewDao();
		Proj_viewDao project = new Proj_viewDao();
		Timeline_ViewDao timeline = new Timeline_ViewDao();
				
		List port_list = portfolio.portfolio_info(102);
		List mem_list = member.member_info();
		List proj_list = project.project_info();
		List time_list = timeline.timeline_info(101);	//*************101은 임시값  timeline_info(mem_id)로 수정***********
	
		//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
		req.setAttribute("port_list", port_list);
		req.setAttribute("mem_list", mem_list);
		req.setAttribute("proj_list", proj_list);			
		req.setAttribute("time_list", time_list);				
		
		if(cmd.equals("LOGIN")){
			url="/main.jsp";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
