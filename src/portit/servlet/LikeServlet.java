package portit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.NotificationDao;
import portit.model.dao.PortfolioDao;
import portit.model.dto.Notification;

@SuppressWarnings("serial")
@WebServlet("/like")
public class LikeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		PortfolioDao portfolioDao = new PortfolioDao();
		NotificationDao notiDao = new NotificationDao();
		Notification noti = new Notification();
		
		int pf_id = Integer.parseInt(req.getParameter("pf_id"));
		int mem_id = Integer.parseInt(req.getParameter("mem_id"));
		String cmd = req.getParameter("cmd");

		noti.setMem_id_sender(mem_id);
		noti.setNt_type_id(pf_id);
		noti.setMem_id_receiver(portfolioDao.pfidTomemid(pf_id));
		noti.setNt_type("like");
		
		int likes = 0;
		if ("like".equals(cmd)) {
			likes = portfolioDao.addLike(mem_id, pf_id);
			
			notiDao.insert(noti); // 알림테이블에 넣기		
			
		} else if ("dislike".equals(cmd)) {
			likes = portfolioDao.minusLike(mem_id, pf_id);
			
			int nt_id = notiDao.getNt_id(noti);
			notiDao.delete(nt_id);
		}
		
		resp.getWriter().print(likes);
	}
}