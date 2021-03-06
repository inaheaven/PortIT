package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.PortfolioDao;
import portit.model.dto.Portfolio;

/**
 * 포트폴리오 조회 컨트롤러
 *
 */
public class PortfolioViewController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		int articleId = Integer.parseInt(req.getParameter("id"));
		PortfolioDao portfolioDao = new PortfolioDao();
		Portfolio portfolio = portfolioDao.selectOne(articleId);
		req.setAttribute("portfolio", portfolio);
		
		// 뷰 URL 반환
		String viewUrl = "fwd:/pfDetail.jsp";
		return viewUrl;
	}

}
