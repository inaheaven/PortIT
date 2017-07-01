package portit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.PortfolioDao;
import portit.model.dto.Portfolio;

@SuppressWarnings("serial")
@WebServlet("/portfolio")
public class PortfolioViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int articleId = Integer.parseInt(req.getParameter("id"));
		
		String viewUrl = "";
		
		PortfolioDao portfolioDao = new PortfolioDao();
		Portfolio portfolio = portfolioDao.selectOne(articleId);
		//ServletContext sc = req.getServletContext();
		req.setAttribute("portfolio", portfolio);
		viewUrl = "fwd:/pfDetail.jsp";

		System.out.println("viewUrl: " + viewUrl.substring(0, 2) + "/" + viewUrl.substring(4));
		RequestDispatcher rd = null;
		if (viewUrl.substring(0, 3).equals("inc")) {
			rd = req.getRequestDispatcher(viewUrl.substring(4));
			rd.include(req, resp);
		} else if (viewUrl.substring(0, 3).equals("fwd")) {
			rd = req.getRequestDispatcher(viewUrl.substring(4));
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(viewUrl.substring(4));
		}
	}
	
}
