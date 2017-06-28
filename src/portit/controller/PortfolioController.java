package portit.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioController implements Controller {

	@Override
	public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		Map<String, String> map = (Map<String, String>) sc.getAttribute("formdata");
		return null;
	}
}
