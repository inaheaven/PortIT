package portit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 * 포트폴리오 작성 컨트롤러
 *
 */
public class PortfolioAddController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// UploadServlet이 전달해준 데이터 받아오기
		Map<String, Object> formData = (Map<String, Object>) req.getAttribute("formData");
		List<String> fileList = (List<String>) formData.get("fileList");

		// 태그, 공동 작업자 관련 처리
		List<Tag> languageTagList = (List<Tag>) formData.get("tagLang");
		List<Tag> toolTagList = (List<Tag>) formData.get("tagTool");
		List<Tag> fieldTagList = (List<Tag>) formData.get("tagField");
		List<Profile> coworkerList = (List<Profile>) formData.get("coworker");
		for (String key : formData.keySet()) {
			if (formData.get(key) != null) {
				System.out.println(key + " : " + formData.get(key));
			}
		}

		// 파일 관련 처리
		List<Media> mediaList = new ArrayList<Media>();
		for (int i = 0; i < fileList.size(); i++) {
			mediaList.add(new Media().setMl_type("portfolio").setMl_path(fileList.get(i)));
		}

		// DTO에 추가
		PortfolioDao portfolioDao = new PortfolioDao();
		Portfolio portfolio = new Portfolio();
		try {
			portfolio.setMem_id(Integer.parseInt(formData.get("mem_id").toString()))
					.setPf_title((String) formData.get("pf_title"))
					.setPf_intro(formData.get("pf_intro") != null
							? ((String) formData.get("pf_intro")).replaceAll("\\r\\n", "<br />") : "")
					.setPf_startdate(
							(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) formData.get("pf_startdate")))
					.setPf_enddate((Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) formData.get("pf_enddate")))
					.setPf_numofperson(Integer.parseInt((String) formData.get("pf_numofperson")))
					.setPf_url((String) formData.get("pf_url")).setPf_tags_language(languageTagList)
					.setPf_tags_tool(toolTagList).setPf_tags_field(fieldTagList).setPf_mediaList(mediaList)
					.setPf_coworkers(coworkerList);
			// DAO의 추가 메서드 호출
			// portfolioDao.insert(portfolio);
		} catch (Exception e) {
			System.out.println("데이터가 데이터베이스에 저장되지 못했습니다.");
			e.printStackTrace();
		}

		// 뷰 URL 반환
		String viewUrl = "rdr:/page?page=myPfList";
		return viewUrl;
	}

}