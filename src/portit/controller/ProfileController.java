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
		
		int mem_id = Integer.parseInt(req.getParameter("mem_id"));
		req.setAttribute("mem_id", mem_id);
		
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
		int prof_skill_level = Integer.parseInt(req.getParameter("prof_skill_level"));
		int prof_follower = Integer.parseInt(req.getParameter("prof_follower"));
		
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
		prof_reg.setProf_skill_level(prof_skill_level);
		prof_reg.setProf_skill_level(prof_follower);
		
		////////////////////////////101 -> mem_id로///////////////////////////////
		profileDao.addprofile(prof_reg, 101);
		req.setAttribute("prof_reg", prof_reg);
		List list = profileDao.getProfile(prof_reg, 101);	
		
		if(cmd.equals("REGISTER")){
			//return  new RegisterCommand();
			url="/myProf.jsp";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
		
		
		
		
		
		
		
		/*
		if(cmd!=null && cmd.equals("REGISTER")){
			
		Profile dto = new Profile();
		String prof_name = req.getParameter("prof_name");
		String prof_nick = req.getParameter("prof_nick");
		String prof_intro = req.getParameter("prof_intro");
		dto.setProf_name(prof_name);
		dto.setProf_nick(prof_nick);
		dto.setProf_intro(prof_intro);
	
		ProfileDao dao = new ProfileDao();
		Profile resultDto = dao.addprofile(dto, mem_id);
		req.setAttribute("profile", resultDto);
		
		
		 * dto �뿉 蹂��닔 �꽔湲�
		 * dao濡� insert 泥섎━ Dto dto = dao.addprofile(dto);
		 * dto瑜� request �뿉 �냽�꽦�쑝濡� 吏��젙 request.setAttribute("dto", dto)
		 * 
		 * */
		
		//�솕硫댁뿉�꽌
		/* �젣�씪 �쐞�뿉
		 * <% 
		 *   Dto dto = request.getAttribute("dto");
		 * %>
		 * value="<%=dto.get??? %>"
		 * */
		
	/*	
	}else if(cmd!=null && cmd.equals("UPDATE")){
		Profile dto = new Profile();
		String prof_name = req.getParameter("Prof_name");
		String prof_nick = req.getParameter("Prof_nick");
		String prof_intro = req.getParameter("Prof_intro");
		dto.setProf_name(prof_name);
		dto.setProf_nick(prof_nick);
		dto.setProf_intro(prof_intro);
	
		ProfileDao dao = new ProfileDao();
		dao.updateProfile(dto);
		System.out.println("updatetest");
	}
*/
	/*public Profile profile(HttpServletRequest req){
		String prof_nick = req.getParameter("prof_nick");*/
	}
}
