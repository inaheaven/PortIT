package portit.model.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import portit.model.dao.MassageDao;
import portit.model.dao.ProjectDao;
import portit.model.dto.MessageDto;

public class ProjcetModel {
	
	private HttpServletRequest req;
	private String login_id;			//Dao로 전달할 mem_id (Who)
	private ArrayList list;			//Ctrl에 반환할 List 변수.
	
	ProjectDao dao= new ProjectDao();
	
	
	public ProjcetModel(HttpServletRequest _req, String _login_id){
		this.req=_req;
		this.login_id=_login_id;
		//모델_DAO
		//해당 사용자에 관한 db를 얻어야하기때문에 식별자를 전달한다.
	}

	public List appyPjList(String login_id) {
		list=(ArrayList)dao.applyProjectList(login_id);
		return list;
	}
	
	public ArrayList regPjList(String login_id){
		
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
			pj.add(dao.ApplyMemList(pf_id));
			
			
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
	public void pjCancle(String pf_id,String login_id) {
		//내가 지원
		dao.mem_delete(pf_id,login_id);
	}
	//팀원커트
	public void mem_delete(String pf_id, String mem_id) {
		//남이 지원.
		dao.mem_delete(pf_id,mem_id);
	}

	
}
	






