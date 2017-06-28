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

import portit.model.dao.BookmarkDao;
import portit.model.dao.FollowDao;
import portit.model.dto.Follow;
import portit.model.dto.Portfolio;

@WebServlet("/follow")
public class FollowController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");

		String cmd = req.getParameter("cmd");
		String url = null;
		if (cmd.equals("SELECT")) {
			FollowDao flDao = new FollowDao();
			List<Follow> resultFollow = flDao.getFollow();
			if (resultFollow.isEmpty()) {
				resultFollow = new ArrayList<>();
			}
			
			
			req.setAttribute("portfolio", resultPortfolio);
			url = "myFollowing.jsp";

			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req, resp);

		} else if (cmd.equals("FOLLOWDELETE")) {
			FollowDao flDao = new FollowDao();
			int fw_id = 0;
			if (req.getParameter("fw_id") != null) {
				fw_id = Integer.parseInt(req.getParameter("fw_id"));
				flDao.deleteFollow(fw_id);

				List<Follow> resultFollow = flDao.getFollow();
				if (resultFollow.isEmpty()) {
					resultFollow = new ArrayList<>();
				}
				req.setAttribute("follow", resultFollow);

			}
			url = "myFollowing.jsp";

			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req, resp);

		}
	}

}
