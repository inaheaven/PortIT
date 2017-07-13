package portit.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import portit.model.dao.PortfolioDao;
import portit.model.dao.ProfileDao;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;
import portit.util.FileUpload;

@WebServlet("/profReg")
public class ProfileController extends HttpServlet {
	/**
	 * 업로드할 파일이 저장될 디렉토리 이름(/{UPLOAD_DIR})
	 */
	private static final String UPLOAD_DIR = "upload";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("test");
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();
		int loginId = (int)session.getAttribute("loginId");
		System.out.println("login Id :" +loginId);
		req.setAttribute("loginId", loginId);
		
		
		String cmd = req.getParameter("cmd");
		String url = "";

		//int mem_id = Integer.parseInt(req.getParameter("mem_id"));
		//req.setAttribute("mem_id", mem_id);

	

		int prof_follower =0;
		String prof_img = req.getParameter("prof_img");
		String prof_background = req.getParameter("prof_background");
		String prof_name = req.getParameter("prof_name");
		String prof_nick = req.getParameter("prof_nick");
		String prof_intro = req.getParameter("prof_intro");
		String prof_website = req.getParameter("prof_website");
		String prof_facebook = req.getParameter("prof_facebook");
		String prof_github = req.getParameter("prof_github");
		String[] lang_list1 = req.getParameterValues("tag_lang");
		String[] tool_list1 = req.getParameterValues("tag_tool");
		String[] field_list1 = req.getParameterValues("tag_field");
		String[] tag_skill1 = req.getParameterValues("tag_skill");
		String[] prof_skill_level1 = req.getParameterValues("prof_skill_level");
		
		
		List<String> tag_lang = new ArrayList();
		for(String tags : lang_list1){
			tag_lang.add(tags);
		}
		
		List<String> tag_tool = new ArrayList();
		for(String tags : tool_list1){
			tag_tool.add(tags);
		}
		
		List<String> tag_field = new ArrayList();
		for(String tags : field_list1){
			tag_field.add(tags);
		}
		
		List<String> tag_skill = new ArrayList();
		for(String tags : tag_skill1){
			tag_skill.add(tags);
		}
		
		List<String> prof_skill_level = new ArrayList();
		for(String tags : prof_skill_level1){
			prof_skill_level.add(tags);
		}
		
				
		System.out.println(tag_lang);
		System.out.println(tag_tool);
		System.out.println(tag_field);
		System.out.println("tag_skill: "+ tag_skill);
		System.out.println("prof_skill_level: "+ prof_skill_level);
		
		System.out.println("name :" + prof_name);
		

//		int prof_follower = Integer.parseInt(req.getParameter("prof_follower"));
		
		ProfileDao profileDao = new ProfileDao();
		Profile prof_reg = new Profile();

		prof_reg.setProf_img(prof_img);
		prof_reg.setProf_background(prof_background);
		prof_reg.setProf_follower(prof_follower);
		prof_reg.setProf_name(prof_name);
		prof_reg.setProf_nick(prof_nick);
		prof_reg.setProf_intro(prof_intro);
		prof_reg.setProf_website(prof_website);
		prof_reg.setProf_facebook(prof_facebook);
		prof_reg.setProf_github(prof_github);
	
		prof_reg.setTag_lang(tag_lang);
		prof_reg.setTag_tool(tag_tool);
		prof_reg.setTag_field(tag_field);
		prof_reg.setTag_skill(tag_skill);
		prof_reg.setProf_skill_level(prof_skill_level);
		
		profileDao.addprofile(prof_reg, loginId);
		req.setAttribute("prof_reg", prof_reg);

	/*
		if (!ServletFileUpload.isMultipartContent(req)) {
			try {
				throw new Exception("요청이 multipart/form-data로 인코딩되지 않았습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ServletContext sc = req.getServletContext();
		String saveDir = sc.getRealPath("") + UPLOAD_DIR;
//		String saveDir = "C://" + UPLOAD_DIR;
		

		// 저장할 디렉토리가 없으면 생성
		File fileSaveDir = new File(saveDir);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		factory.setRepository(new File(saveDir));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 20);
		upload.setHeaderEncoding("UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest((RequestContext) req);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// isFormField()의 반환값이 true이면 일반 파라미터로 처리
					map.put(item.getFieldName(), item.getString());
					sc.setAttribute("formdata", map);
				} else {
					// isFormField()의 반환값이 false이면 파일로 처리
					if (item.getContentType().startsWith("image/")) {
						String path = saveDir + File.separator + getFileName(item);
						item.write(new File(path));
//						fileNames.add(path);
					} else {
						throw new Exception("이미지파일만 업로드할 수 있습니다.");
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/

		
		if (cmd.equals("REGISTER")) {
			ProfileDao dao = new ProfileDao();
		//	Profile prof= dao.selectOne(loginId);
		//	req.setAttribute("prof", prof);
			//url = "/page?page=myProfUpdate";
		}

		else if (cmd.equals("UPDATE")) {
			ProfileDao dao = new ProfileDao();
		//	Profile prof= dao.selectOne(loginId);
		//	req.setAttribute("prof", prof);
			//url = "/page?page=myProfUpdate";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);

	}

	/**
	 * 파일명을 인코딩
	 * 
	 * @param item
	 * @return MD5 인코딩된 파일명
	 */
	private String getFileName(FileItem item) {
		String[] fileName = null;
		// content-disposition 헤더 읽어오기
		String fn = item.getName().trim();
		fileName = new String[2];
		fileName[0] = encodeFileName(fn.substring(0, fn.lastIndexOf(".")));
		fileName[1] = fn.substring(fn.lastIndexOf(".") + 1);
		return fileName[0] + "." + fileName[1];
	}

	/**
	 * 문자열을 MD5 인코딩
	 * 
	 * @param str
	 *            문자열
	 * @return 인코딩된 문자열
	 */
	private String encodeFileName(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte[] ba = md.digest();
			for (byte b : ba) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			sb = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 구분자가 있는 문자열을 문자열 배열로 변환
	 * @param str 구분자가 있는 문자열
	 * @return 문자열 배열
	 */
	private String[] toArray(Object data) {
		StringTokenizer tkn = new StringTokenizer((String) data, ",");
		String[] arr = new String[tkn.countTokens()];
		for (int i = 0; tkn.hasMoreElements(); i++) {
			arr[i] = tkn.nextToken().trim();
		}
		return arr;
	}
}
