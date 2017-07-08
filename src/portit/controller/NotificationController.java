package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.NotificationDao;

@WebServlet("/nt")
public class NotificationController extends HttpServlet {	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();
		int loginId = (int)session.getAttribute("loginId");
				
		NotificationDao nt = new NotificationDao();
		
		String act = req.getParameter("act");
		
		// 삭제
		if(act.equals("delete")) {
			int nt_id = Integer.parseInt(req.getParameter("id")); // 선택한 nt_id
			nt.delete(nt_id);
			resp.sendRedirect("/page?page=myNotification&sort=all");
		}
		// 알림 읽음
		else if(act.equals("read")) {
			int nt_id = Integer.parseInt(req.getParameter("id")); // 선택한 nt_id
			nt.isRead(nt_id);
			resp.sendRedirect("/page?page=myNotification&sort=all");
		}
		// isread 가져오기
		else if(act.equals("getread")) {
			int nt_id = Integer.parseInt(req.getParameter("id")); // 선택한 nt_id	
			char isread = nt.getNt_isread(nt_id);
			out.println(isread);
		}
		// 구분 필터링
		else if(act.equals("select")) {
			String sort = req.getParameter("sort"); // 선택한 정렬 value 값을 가져옴
			req.setAttribute("sort2", sort); // value 값을 'sort2'라는 request 변수에 저장
			resp.sendRedirect("/page?page=myNotification&sort=" + sort);
		}
	}
}
