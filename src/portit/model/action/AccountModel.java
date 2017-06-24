package portit.model.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import portit.model.dao.AccountDao;
import portit.model.dao.MassageDao;
import portit.model.dto.AccountDto;
import portit.model.dto.MessageDto;

public class AccountModel {
	
	private HttpServletRequest req;
	private int login_id;			//Dao로 전달할 mem_id (Who)
	private AccountDao dao;
	private ArrayList list;			//Ctrl에 반환할 List 변수.
	
	
	//Ctrl로부터 전달받을 정보
	//Request에서 정보뽑는것은 Model에서....
	private String email;				//이메일
	private String currentPw;			//현재 비밀번호
	private String newPw;				//바꿀 비민번호
	private String confirmNewPw;		//비밀번호 확인.
	
	boolean IdentityVerification;
	
	
	public AccountModel(HttpServletRequest _req, int _login_id){
		this.req=_req;
		this.login_id=_login_id;
		

		//request로 부터. 입력정보를 초기화...
		this.email=req.getParameter("email");
		this.currentPw=req.getParameter("currentPw");
		this.newPw=req.getParameter("newPw");
		this.confirmNewPw=req.getParameter("confirmNewPw");
		
		
		//DAO. 싱글톤패턴으로 테스트... 오류날수도 있음.
		this.dao=AccountDao.getInstance();
		dao.setLogin_id(_login_id);
	}
	
	
	
	
	
	//Update
	public void alterAccount(){
		//임의의 메일값값을 Test
		this.IdentityVerification=dao.validationService("kin7729@naver.com","1111");;
		
		//this.IdentityVerification=dao.validationService(email,currentPw);;//본인인증.
	
		 
		 
		//ID와 PW가 맞다면.
		if(IdentityVerification==true){
			
			System.out.println("MOdel: 비밀번호 일치합니다.");			
			
			//새로운 비밀번호가 일치하다면... 
			//dao.alterAccount(Integer.parseInt(this.newPw));
		}
		//ID와 PW가 틀렸다면.
		else{
			//경고메세지!!!
			System.out.println("MOdel: 비밀번호 일치하지 않습니다.");
		}
	}
	
	
	
	//삭제하기.
	public void deleteAccount(){
		this.IdentityVerification=dao.validationService("kin7729@naver.com","1111");
		//log_id의 pw와 일치한지....
		// dao에서 유효성검사 쿼리가 그대로 쓰인다.
		
		dao.deleteAccount();
	}
	
	
}






