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
import portit.model.dao.PortfolioDao;
import portit.model.dto.Portfolio;


@WebServlet("/pfList")
public class MyPortfoliolistController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		int loginId = (int)session.getAttribute("loginId");
		
		String cmd = req.getParameter("cmd");
		String url = null;
		
		
		if (cmd.equals("MYPORTFOLIO")) {
			PortfolioDao ptDao = new PortfolioDao();
			List<Portfolio> pfPortfolio = ptDao.myPortfolio(loginId);
			
			req.setAttribute("portfolio", pfPortfolio);
			url="myPfList.jsp";
			
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req, resp);
		}
		else if (cmd.equals("MYPORTFOLIODELETE")) {
			PortfolioDao pfDao = new PortfolioDao();
			int prof_pf_id = 0;
			if (req.getParameter("pf_id") != null) {
				prof_pf_id = Integer.parseInt(req.getParameter("pf_id"));
				pfDao.deleteMyport(prof_pf_id);
				List<Portfolio> pfPortfolio = pfDao.myPortfolio(prof_pf_id);
				if (pfPortfolio.isEmpty()) {
					pfPortfolio = new ArrayList<>();
				}
				req.setAttribute("portfolio", pfPortfolio);

				resp.sendRedirect("/page?page=myPfList");
			}

		}
	}
}
