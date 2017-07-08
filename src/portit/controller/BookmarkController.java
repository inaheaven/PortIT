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

import portit.model.dao.BookmarkDao;
import portit.model.dto.Portfolio;

@WebServlet("/bmk")
public class BookmarkController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		int loginId = (int)session.getAttribute("loginId");
		
		String cmd = req.getParameter("cmd");
		String url = null;
		if (cmd.equals("BOOKMARK")) {
			req.setAttribute("onload", "S");
			//pf_id를 가져온다고 하고 진행

			//존재하면 DELETE 없으면 INSERT
			int pf_id = 0, mem_id = 0;
			if (req.getParameter("pf_id") != null) {
				pf_id = Integer.parseInt(req.getParameter("pf_id"));
			}
			if (req.getParameter("mem_id") != null) {
				mem_id = Integer.parseInt(req.getParameter("mem_id"));
			}
			BookmarkDao bmDao = new BookmarkDao();
			bmDao.addBookmark(pf_id, mem_id);

			//엑스버튼 누를때 dto에서 deletebookmark 실행page?page=myBookmark

			//포트폴리오 상세페이지에서 북마큰 버튼 누르면 북마크되는 dao메소드 실행

			//포트폴리오 상세페이지에서 북마크 버튼을 다시 눌러서 비활성화시 북마크 취
			
		} else if (cmd.equals("MYBOOKMARK")) {
			BookmarkDao bmDao = new BookmarkDao();
			List<Portfolio> resultPortfolio = bmDao.myBookmark(loginId);
			
			req.setAttribute("portfolio", resultPortfolio);
			url="myBookmark.jsp";
			
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req, resp);
			
		}else if (cmd.equals("MYBOOKMARKDELETE")) {
			BookmarkDao bmDao = new BookmarkDao();
			int bm_id=0;
			if(req.getParameter("bm_id")!=null){
				bm_id = Integer.parseInt(req.getParameter("bm_id"));
				bmDao.deleteBookmark(bm_id);
				List<Portfolio> resultPortfolio = bmDao.myBookmark(bm_id);
				if(resultPortfolio.isEmpty()){
					resultPortfolio = new ArrayList<>();
				}
				req.setAttribute("portfolio", resultPortfolio);
				
				resp.sendRedirect("/bmk?cmd=MYBOOKMARK");
			}
/*			url="myBookmark.jsp";
			
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req, resp);
*/			
		}
	}

}
