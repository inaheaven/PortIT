package portit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import portit.model.action.ProjcetModel;
import portit.model.dao.Portfolio_ViewDao;
import portit.model.dao.ProjectDao;
import portit.model.dto.Project;
import portit.model.dto.ProjectApp_mem;
/**
 * 
 * @author isyi
 * 로그인 성공시, main화면으로 이동하기 위한 컨트롤러
 *
 */
@WebServlet(urlPatterns="/myproj")
public class MyProjController extends HttpServlet{
	
	//0708!
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
		
		//요청에서 저장된 session값에 접근할수 있다.
		HttpSession session = req.getSession();
		
		/*
			
			남은적업
				아코디언 오류
				내가 지원한 프로젝트 업로드...
		 */
		
		
		/*
		 전달받을 파라미터 : 
		 -CMD:경로(ist), 
		 -action:행동(delete)
		 -매개변수:mem_ID, PF_ID.... 
		 */
		
		String cmd = req.getParameter("cmd");
		String action=req.getParameter("cmdAction");
		String param=req.getParameter("param");
		String param2=req.getParameter("param2");
		String longinId=(String)session.getAttribute("longinId");
		String url = null;
		String mem_id=null;
		String pf_id=null;
		
		
		System.out.println("CTRL수치전달"+longinId);
		
		ProjcetModel model=new ProjcetModel(req, longinId);

		
		//dao 선언
		Portfolio_ViewDao dao = new Portfolio_ViewDao();
		ProjectDao daoPRO = new ProjectDao();
		List list;
		
		
		System.out.println("CTRL_CMD  "+cmd);
		System.out.println("CTRL파라미터  "+param);
		System.out.println("CTRL파라미터2  "+param2);
		System.out.println("CTRLcmd액션  "+action);
		

		
		//포트폴리오 리스트
		if("list".equals(cmd)){
		
			
	//수락버튼				
			if("join".equals(action)){
				//param:해당 지원자의 mem_id
				
				//y값으로 업데이트하기.
				model.pjJoin(param);
			}
			
			
	//PJ삭제버튼.	
			else if("delete".equals(action)){
				//param:해당 팀원의 mem_id
				dao.deletePortforio(param);
			}
			
			
			
	//멤버삭제.
			else if("mem_delete".equals(action)){
				//param1: PJ_ID				What?
				//param1: 지원지의 mem_id		Who?
				
				model.mem_delete(param,param2);
			}
			
			
			
	//(n)지원 취소
			else if("cancle".equals(action)){
				//param: 지원PJ의 PJ_ID
				
				
				model.pjCancle(param);
			}
			
			
			
			
			
			
			//내가 등록한 프로젝트 정보...
			list= model.regPjList();
			req.setAttribute("regPjInform", list);
			
			
			
			//내가 지원한 프로젝트
			list= model.appyPjList();
			req.setAttribute("applyProjectList", list);
			
			
			url="/myProjList.jsp";
/*			
			등록한 PJ 테스트완료
			
			//테스트해보기...
			//첫번째 프로젝트.
			ArrayList pj =(ArrayList)list.get(0);
			
			//0: 프로젝트정보 
			Project pjInfrom =(Project)pj.get(0);
			
			//1: 지원자 정보.
			ArrayList peoples = (ArrayList) pj.get(1);
			
			//1-1: 첫번째 지원자 정보.
			ProjectApp_mem p1 =(ProjectApp_mem) peoples.get(0);
			
			//정보확인 p1의 이름.
			System.out.println("사람이름 "+ p1.getName());
			//프로젝트의 이름.
			System.out.println("pj명  "+pjInfrom.getProj_title());
		*/
			
		
		}
		
		else if("modify".equals(cmd)){
			req.setAttribute("pj_id", param);
			url="/수정페이지.jsp";
		}
		else if("pj_detail".equals(cmd)){
			req.setAttribute("pj_id", param);
			url="/프로필페이지.jsp";
		}
		
		
		
		req.setAttribute("pageName", url);
		RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
		view.forward(req,resp);
	}
}
