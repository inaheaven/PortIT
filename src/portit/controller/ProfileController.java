package portit.controller;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.portlet.PortletFileUpload;

import portit.model.dao.ProfileDao;
import portit.model.dto.Profile;
@WebServlet("/register")
public class ProfileController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("test");
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		resp.setContentType("text/html; charset=UTF-8");
		      
		String cmd = req.getParameter("cmd");
		String url ="";
		
		//int mem_id = Integer.parseInt(req.getParameter("mem_id"));
		//req.setAttribute("mem_id", mem_id);
		
		ProfileDao profileDao = new ProfileDao();
		Profile prof_reg = new Profile();
		
		String prof_img = req.getParameter("prof_img");
		String prof_background = req.getParameter("prof_background");
		String prof_name = req.getParameter("prof_name");
		String prof_nick = req.getParameter("prof_nick");
		String prof_intro = req.getParameter("prof_intro");
		String prof_website = req.getParameter("prof_website");
		String tag_name = req.getParameter("tag_name");
		String tag_name2 = req.getParameter("tag_name2");
		String tag_name3 = req.getParameter("tag_name3");
		String tag_name4 = req.getParameter("tag_name4");
		String tag_name5 = req.getParameter("tag_name5");
		String tag_name6 = req.getParameter("tag_name6");
		
		//null값이 넘어 오는 경우 오류 처리
		int prof_skill_level = 0;
		int prof_skill_level2  = 0;
		int prof_skill_level3  = 0;
		
		if(req.getParameter("prof_skill_level")==null || req.getParameter("prof_skill_level").equals("")){
			prof_skill_level = 0;
		}
		else{
			prof_skill_level = Integer.parseInt(req.getParameter("prof_skill_level"));
		}
		
		if(req.getParameter("prof_skill_level2")==null || req.getParameter("prof_skill_level").equals("")){
			prof_skill_level2 = 0;
		}
		else{
			prof_skill_level2 = Integer.parseInt(req.getParameter("prof_skill_level2"));
		}
		
		if(req.getParameter("prof_skill_level3")==null || req.getParameter("prof_skill_level").equals("")){
			prof_skill_level3 = 0;
		}
		else{
			prof_skill_level3 = Integer.parseInt(req.getParameter("prof_skill_level3"));
		}
		
		int prof_follower = Integer.parseInt(req.getParameter("prof_follower"));
		String prof_facebook = req.getParameter("prof_facebook");
		String prof_github = req.getParameter("prof_github");
		
		prof_reg.setProf_img(prof_img);
		prof_reg.setProf_background(prof_background);
		prof_reg.setProf_name(prof_name);
		prof_reg.setProf_nick(prof_nick);
		prof_reg.setProf_intro(prof_intro);
		prof_reg.setProf_website(prof_website);
		prof_reg.setTag_name(tag_name);
		prof_reg.setTag_name2(tag_name2);
		prof_reg.setTag_name3(tag_name3);
		prof_reg.setTag_name4(tag_name4);
		prof_reg.setTag_name5(tag_name5);
		prof_reg.setTag_name6(tag_name6);
		prof_reg.setProf_skill_level(prof_skill_level);
		prof_reg.setProf_skill_level2(prof_skill_level2);
		prof_reg.setProf_skill_level3(prof_skill_level3);
		prof_reg.setProf_follower(prof_follower);
		prof_reg.setProf_facebook(prof_facebook);
		prof_reg.setProf_github(prof_github);
		
		////////////////////////////102 -> mem_id로///////////////////////////////
		//INSERT
		profileDao.addprofile(prof_reg, 103);
		req.setAttribute("prof_reg", prof_reg);
		
		if(cmd.equals("REGISTER")){
			//return  new RegisterCommand();
			url="/myPorfUpdate.jsp";
		}
		
		else if(cmd.equals("UPDATE")){
			//UPDATE
			profileDao.updateProfile(prof_reg, 103);
			url="/myPorfUpdate.jsp";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
		

	}
}
