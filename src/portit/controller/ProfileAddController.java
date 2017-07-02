package portit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProfileDao;
import portit.model.dto.Media;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * 포트폴리오 작성 컨트롤러
 *
 */
public class ProfileAddController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// UploadServlet이 전달해준 데이터 받아오기
		Map<String, String> formData = (Map<String, String>) req.getAttribute("formData");
		List<String> fileNames = (List<String>) req.getAttribute("fileNames");
		for (String key : formData.keySet()) {
			System.out.printf("%s : %s\n", key, formData.get(key));
		}
		for (String filename : fileNames) {
			System.out.println(filename);
		}
		
		// 태그 관련 처리
		List<Tag> langTagList = new ArrayList<Tag>();
		List<Tag> toolTagList = new ArrayList<Tag>();
		List<Tag> fieldTagList = new ArrayList<Tag>();
		for (String key : formData.keySet()) {
			if (key.equals("prof_tags_language")) {
				String[] prof_tags_language = toArray(formData.get(key));
				for (int i = 0; i < prof_tags_language.length; i++) {
					langTagList.add(new Tag().setTag_type("language").setTag_name(prof_tags_language[i]));
				}
			} else if (key.equals("prof_tags_tool")) {
				String[] prof_tags_tool = toArray(formData.get(key));
				for (int i = 0; i < prof_tags_tool.length; i++) {
					toolTagList.add(new Tag().setTag_type("tool").setTag_name(prof_tags_tool[i]));
				}
			} else if (key.equals("prof_tags_field")) {
				String[] prof_tags_field = toArray(formData.get(key));
				for (int i = 0; i < prof_tags_field.length; i++) {
					fieldTagList.add(new Tag().setTag_type("field").setTag_name(prof_tags_field[i]));
				}
			}
		}
		
		// 파일 관련 처리
		List<Media> mediae = new ArrayList<Media>();
		for (int i = 0; i < fileNames.size(); i++) {
			mediae.add(new Media().setMl_type("portfolio").setMl_path(fileNames.get(i)));
		}
		
		// DTO에 추가
		ProfileDao profileDao = new ProfileDao();
		Profile profile = new Profile();
		profile.setMem_id((int) req.getSession().getAttribute("loginId"))
		.setProf_nick(formData.get("prof_nick"))
		.setProf_name(formData.get("prof_name"))
		.setProf_intro(formData.get("prof_intro"))
		.setProf_img(mediae.get(0).getMl_path())
		.setProf_background(mediae.get(1).getMl_path())
		.setProf_email((String) req.getSession().getAttribute("loginEmail"))
		.setProf_website(formData.get("prof_website"))
		.setProf_github(formData.get("prof_github"))
		.setProf_facebook(formData.get("prof_facebook"))
		.setProf_tags_language(langTagList)
		.setProf_tags_tool(toolTagList)
		.setProf_tags_field(fieldTagList);
		// DAO의 추가 메서드 호출
		profileDao.addprofile(profile);
		
		// 뷰 URL 반환
		String viewUrl = "rdr:/page?page=main";
		return viewUrl;
	}
	
	/**
	 * 구분자가 있는 문자열을 문자열 배열로 변환
	 * @param str 구분자가 있는 문자열
	 * @return 문자열 배열
	 */
	private String[] toArray(String str) {
		StringTokenizer tkn = new StringTokenizer(str, ",");
		String[] arr = new String[tkn.countTokens()];
		for (int i = 0; tkn.hasMoreElements(); i++) {
			arr[i] = tkn.nextToken().trim();
		}
		return arr;
	}
}
