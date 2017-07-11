package portit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.PortfolioDao;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;
import portit.util.FileUpload;

/**
 * 포트폴리오 수정 컨트롤러
 *
 */
public class PortfolioEditController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// PostServlet이 전달해준 데이터 받아오기
		Map<String, Object> formData = (Map<String, Object>) req.getAttribute("formData");
		List<String> fileNames = (List<String>) formData.get("fileList");

		// 태그, 공동 작업자 관련 처리
		List<Tag> languageTagList = new ArrayList<Tag>();
		List<Tag> toolTagList = new ArrayList<Tag>();
		List<Tag> fieldTagList = new ArrayList<Tag>();
		List<Profile> coworkerList = new ArrayList<Profile>();
		String[] pf_tags_language = (String[]) formData.get("pf_tags_language");
		for (int i = 0; i < pf_tags_language.length; i++) {
			languageTagList.add(new Tag().setTag_type("language").setTag_name(pf_tags_language[i]));
		}
		String[] pf_tags_tool = (String[]) formData.get("pf_tags_tool");
		for (int i = 0; i < pf_tags_tool.length; i++) {
			toolTagList.add(new Tag().setTag_type("tool").setTag_name(pf_tags_tool[i]));
		}
		String[] pf_tags_field = (String[]) formData.get("pf_tags_field");
		for (int i = 0; i < pf_tags_field.length; i++) {
			fieldTagList.add(new Tag().setTag_type("field").setTag_name(pf_tags_field[i]));
		}
		String[] pf_coworkers = (String[]) formData.get("pf_coworkers");
		for (int i = 0; i < pf_tags_language.length; i++) {
			coworkerList.add(new Profile().setProf_nick(pf_coworkers[i]));
		}

		// 파일 관련 처리
		List<Media> mediaList = new ArrayList<Media>();
		for (int i = 0; i < fileNames.size(); i++) {
			mediaList.add(new Media().setMl_type("portfolio").setMl_path(fileNames.get(i)));
		}

		// DTO에 추가
		PortfolioDao portfolioDao = new PortfolioDao();
		Portfolio portfolio = new Portfolio();
		try {
			portfolio.setMem_id((int) formData.get("mem_id")).setPf_id((int) formData.get("pf_id"))
					.setPf_title((String) formData.get("pf_title"))
					.setPf_intro(((String) formData.get("pf_intro")).replaceAll("\\r\\n", "<br />"))
					.setPf_startdate(new SimpleDateFormat("yyyy-MM-dd").parse((String) formData.get("pf_start_date")))
					.setPf_enddate(new SimpleDateFormat("yyyy-MM-dd").parse((String) formData.get("pf_end_date")))
					.setPf_numofperson(Integer.parseInt((String) formData.get("pf_numofperson")))
					.setPf_url((String) formData.get("pf_url")).setPf_tags_language(languageTagList)
					.setPf_tags_tool(toolTagList).setPf_tags_field(fieldTagList).setPf_mediaList(mediaList)
					.setPf_coworkers(coworkerList);
			// DAO의 추가 메서드 호출
			portfolioDao.update(portfolio);
		} catch (ParseException e) {
			System.out.println("데이터가 데이터베이스에 저장되지 못했습니다.");
			e.printStackTrace();
		}

		// 뷰 URL 반환
		String viewUrl = "rdr:/page?page=myPfList";
		return viewUrl;
	}

}