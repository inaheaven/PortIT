package portit.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("page");
		
		//HttpSession session = req.getSession();
		
		String pageName = page + ".jsp";
		//session.setAttribute("pageName", pageName);
		req.setAttribute("pageName", pageName);
		
		RequestDispatcher view = req.getRequestDispatcher("/myTemplate.jsp");
		view.forward(req, resp);		
	}
}
