package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PortfolioController implements Controller {
	
	@Override
	public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		return new String[]{"", ""};
	}
	
}
