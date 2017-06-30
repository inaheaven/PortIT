package portit.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.PortfolioDao;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * 포트폴리오 조회 컨트롤러
 *
 */
public class PortfolioDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pf_id = Integer.parseInt(req.getParameter("id"));
		PortfolioDao portfolioDao = new PortfolioDao();
		portfolioDao.delete(pf_id);
		
		// 뷰 URL 반환
		String viewUrl = "rdr:/page/page?myPfList";
		return viewUrl;
	}

}
