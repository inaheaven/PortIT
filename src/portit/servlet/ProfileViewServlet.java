package portit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.controller.Controller;
import portit.controller.ControllerFactory;
import portit.model.dao.PortfolioDao;
import portit.model.dao.ProfileDao;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;

@SuppressWarnings("serial")
@WebServlet("/profile")
public class ProfileViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String username = req.getParameter("username");
		req.setAttribute("username", username);
		
		Controller profileViewController = ControllerFactory.getInstance().newController("ProfileView");
		String viewUrl = profileViewController.execute(req, resp);
		req.setAttribute("profile", req.getAttribute("profile"));

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
