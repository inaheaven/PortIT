package portit.model.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import portit.model.dao.MassageDao;
import portit.model.dao.Pj_Dao;
import portit.model.dao.ProjectDao;
import portit.model.dto.MessageDto;
import portit.model.dto.Profile;
import portit.model.dto.Project;
import portit.model.dto.ProjectApp_mem;
import portit.model.dto.Tag;
import portit.model.dto.project_detail;

public class ProjectModel {
	
	private HttpServletRequest req;
	private String login_id;			//Dao로 전달할 mem_id (Who)
	private ArrayList list;			//Ctrl에 반환할 List 변수.
	
	ProjectDao dao= new ProjectDao();
	
	
	//Detail 페이지 view를 위한 dao by Cater
	Pj_Dao pj_dao= new Pj_Dao();
	
	
	
	public ProjectModel(){
		
	}
	
	
	public ProjectModel(HttpServletRequest _req, String _login_id){
		this.req=_req;
		this.login_id=_login_id;
		
		//모델_DAO
		//해당 사용자에 관한 db를 얻어야하기때문에 식별자를 전달한다.

	}
	

	
	
	public ArrayList prject_detail_infrom(){
		System.out.println("모델진입");
		
		
		int proj_id=0;
		String id=req.getParameter("proj_id");
		System.out.println("MODEl_param="+id);
		
		
		if(!"".equals(id)){
			proj_id = Integer.parseInt(id);
		}
		
		
		System.out.println("Model_변환="+proj_id);
		
		
		
		//0.최종결과를 담는다.
		// 1) : pj정보	<Project>
		// 2) : 지원자list <list>
		ArrayList result=new ArrayList();
		project_detail pj_inform=new project_detail();
		ArrayList pj_appList=new ArrayList();
		
		
		
		//하나의 DAO포장한다.
		
		//1.프로젝트 정보를 반환받는다.(project)
		//제목,소개글, to
		Project project =pj_dao.getPj_inform(proj_id);
		
		pj_inform.setProj_title(project.getProj_title());
		pj_inform.setProj_intro(project.getProj_intro());
		pj_inform.setProj_to(project.getProj_to());
		pj_inform.setProj_id(project.getProj_id());
		pj_inform.setProj_regdate(project.getProj_regdate());
		pj_inform.setProj_startdate(project.getProj_startdate());
		pj_inform.setProj_period(project.getProj_period());
		pj_inform.setProj_regenddate(project.getProj_regenddate());
		
		
		System.out.println("[MODEL.1]");
		
		
		
		
		//2.미디어정보를 반환받는다.(List) : url
		ArrayList media_url=pj_dao.getPj_media(proj_id);
		
		pj_inform.setMl_path((String)media_url.get(0));
		

		System.out.println("[MODEL.2]");
		
		
		
		
		//3.프로젝트 개설자의 정보를 반환받는다.<Profile> :(이름,닉네임, mem_id,이메일)
		//프로젝트 개설자는 누구인가?
		Profile whoMade= pj_dao.getWhoMade(proj_id);
		
		pj_inform.setProf_id(whoMade.getProf_id());
		pj_inform.setMem_id(whoMade.getMem_id());
		pj_inform.setProf_nick(whoMade.getProf_nick());
		pj_inform.setProf_name(whoMade.getProf_name());
		pj_inform.setProf_intro(whoMade.getProf_intro());
		pj_inform.setProf_img(whoMade.getProf_img());
		pj_inform.setProf_background(whoMade.getProf_background());
		pj_inform.setProf_website(whoMade.getProf_website());
		pj_inform.setProf_github(whoMade.getProf_github());
		pj_inform.setProf_facebook(whoMade.getProf_facebook());
		pj_inform.setProf_regdate(whoMade.getProf_regdate());
		pj_inform.setProf_follower(whoMade.getProf_follower());
		
		
		
		

		System.out.println("[MODEL.3]");
		
		
		
		//4.어떤 태그를 사용했는가?		//List형태로 DTO에 포장한다.
		// tag_use에서 해당 프로젝트에 사용된 정보 반환. 해당 정보가 나타내는 태그구분 태그명 반환.
		ArrayList wanted=pj_dao.getWanted(proj_id);
		
		if(0!=wanted.size()){
		Tag dto =(Tag) wanted.get(0);
		
		pj_inform.setTag_id(dto.getTag_id());
		pj_inform.setTag_type(dto.getTag_type());
		pj_inform.setTag_name(dto.getTag_name());
		
		}else{
			System.out.println("[model]:조회결과가 없습니다.");
		}

		System.out.println("[MODEL.4]");
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		//result.get(1) : 동료정보.
		
		//pj_appList.applicant_inform
		//applicant_inform<List>
		//applicant_inform.get(0) : dto
		//applicant_inform.get(1) : 태그 리스트.
		
		//5.프로젝트 지원자.
		//1)지원자 리스트(10,13 송강호,이정재)		from proj_app
		//2)해당 지원자의 정보(태그,좋아요수,사용한태그,이름,닉네임,mem_id)
		
		
		ArrayList coWork =pj_dao.getWhoApply(proj_id);
		for(int i=0; i<coWork.size(); i++){
			int mem_id = (int)coWork.get(i);
			ArrayList applicant_inform = new ArrayList();
			
			//1.사용자 정보를 담는다.
			Profile applicant= pj_dao.getWhoMade(mem_id);
			applicant_inform.add(applicant);
			
			
			//2.태그정보 해당사용자의 태그정보를 담는다.
			ArrayList cowrker_inform = pj_dao.getCoworker_inform(mem_id);
			applicant_inform.add(cowrker_inform);
			
			
			pj_appList.add(applicant_inform);		//dto:Profile
		}
		
		

		System.out.println("[MODEL.5]");
		
		
		
		//최종결과를 담는다.
		// 1) : pj정보	<Project>
		// 2) : 지원자list <list>
		result.add(pj_inform);
		result.add(pj_appList);
		
		
		return result;
	}
	
	
	
	
	
	public List appyPjList() {
		
		list=(ArrayList)dao.applyProjectList(login_id);
		return list;
	}
	
	
	public ArrayList regPjList(){
		
		//내가 등록한 프로젝트 list
		//이 작업을 담아줄 model하나 필요...
		
		//내가 등록한 PJ명단
		list=(ArrayList)dao.RegPJlistName(login_id);
				//.RegPJlistName(login_id);
		
		
		//종합정보= 유저List+프로젝트정보..
		ArrayList regPjPackage=new ArrayList();
		
		
		for(int i=0; i<list.size();i++){
			//한번돌때마다 하나의 등록 프로젝트....
			
			String pf_id= (String)list.get(i);
			
			ArrayList pj =new ArrayList();
			
			//프로젝트정보
			pj.add(dao.regProjectList(pf_id));

			
			//지원자 정보가 리스트로 반환
	//지원자 정보를 반환받지못하고있음... 반환테스트 확인!!
			pj.add(dao.ApplyMemList(pf_id));
			
			
		/*	
			//Test작업...
			ArrayList app_list =(ArrayList)dao.ApplyMemList(pf_id);
			
			//첫번째 지원자.
			ProjectApp_mem app_mem =(ProjectApp_mem)app_list.get(0);	
			System.out.println("ModelTest="+app_mem.getName());
			
			*/
			
			
			//하나의 폼에 필요한 모든 정보들...
			regPjPackage.add(pj);
		}
		
		//PJ덩어리..
		return regPjPackage;
	}



	
	//수락하기
	public void pjJoin(String mem_id) {
		dao.pjJoin(mem_id);
	}


	//지원취소
	public void pjCancle(String pf_id) {
		//내가 지원
		dao.mem_delete(pf_id,login_id);
	}


	//팀원커트
	public void mem_delete(String pf_id, String mem_id) {
		//남이 지원.
		dao.mem_delete(pf_id,mem_id);
	}



	
	
	
}
	






