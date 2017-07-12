package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;
import portit.model.dto.Project;

/**
 * 포트폴리오 조회 컨트롤러
 *
 */
public class ProjectViewController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		int articleId = Integer.parseInt(req.getParameter("id"));
		ProjectDao projectDao = new ProjectDao();
		/*프로젝트 번호로 특정 프로젝트를 조회하는 메서드*/
		Project project = projectDao.(articleId);
		req.setAttribute("project", project);
		
		// 뷰 URL 반환
		String viewUrl = "fwd:/projDetail.jsp";
		return viewUrl;
	}

}