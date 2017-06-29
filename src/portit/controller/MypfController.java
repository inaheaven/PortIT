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
@WebServlet(urlPatterns="/myportporio")
public class MypfController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
		
		/*
		 전달받을 파라미터 : 
		 -CMD:경로(ist), 
		 -action:행동(delete)
		 -매개변수:mem_ID, PF_ID.... 
		 */
		
		String cmd = req.getParameter("cmd");
		String action=req.getParameter("cmdAction");
		String param=req.getParameter("param");
		String url = null;
		String mem_id=null;
		String pf_id=null;
		
		
		//dao 선언
		Portfolio_ViewDao dao = new Portfolio_ViewDao();
				
		
		System.out.println("CTRL_CMD  "+cmd);
		System.out.println("CTRL파라미터  "+param);
		System.out.println("CTRLcmd액션  "+action);
		
		
		
		
		//포트폴리오 리스트
		if(cmd.equals("list")){
			//파라미터 : pf_id
			
			
			//delete버튼이 눌리면...
			if("delete".equals(action)){
				dao.deletePortforio(Integer.parseInt(param));
			}
			
			//로그정보 MEM_id 102
			List port_list = dao.portfolio_info(102);
			req.setAttribute("port_list", port_list);
			url="/mypage_pf_list.jsp";
		}
		
		
		//포트폴리오수정.
		else if (cmd.equals("modify")){
			//파라미터 : PF_ID
			
			System.out.println("수정페이지로  pf_id값 전달" + param);
			req.setAttribute("param", param);
			url="/수정페이지....jsp";
		}
		
		
		//포트폴리오 상세페이지
		else if (cmd.equals("detail")){
			//파라미터 : PF_ID
			
			System.out.println("상페이지로  pf_id값 전달" + param);
			req.setAttribute("param", param);
			url="/상세페이지....jsp";
		}
		
		
		//프로필상세페이지..
		else if (cmd.equals("profile")){
			//파라미터: MEM_ID
			
			System.out.println("프로필상세  MEM_ID값 전달" + param);
			req.setAttribute("param", param);
			url="/프로필상세 페이지....jsp";
		}
		
		
		//포트폴리오등록하기.
		else if (cmd.equals("add")){
			
			System.out.println("포트폴리오등록하기   페이지로이동.");
			req.setAttribute("param", param);
			url="/포트폴리오등록하기   페이지로이동.jsp";
		}
		
		
		//태그
		else if (cmd.equals("tag")){
			//파라미터:  tag이름
			
			System.out.println("테그검색에 전달" + param);
			req.setAttribute("param", param);
			url="/태그 페이지....jsp";
		}
		
		
		
		req.setAttribute("pageName", url);
		RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
		view.forward(req,resp);
	}
}
