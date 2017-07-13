package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.PortfolioDao;

/**
 * 포트폴리오 삭제 컨트롤러
 *
 */
public class PortfolioDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pf_id = (int) req.getAttribute("pf_id");
		PortfolioDao portfolioDao = new PortfolioDao();
		portfolioDao.delete(pf_id);
		
		// 뷰 URL 반환
		String viewUrl = "rdr:/page?page=myPfList";
		return viewUrl;
	}

}