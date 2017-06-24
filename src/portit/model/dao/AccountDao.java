package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.MessageDto;

public class AccountDao{
	
	//1. Select.계정정보 조회(login_id, pass)
	//2. Update.정보 갱신(
	//3. Delete.정보 삭제.
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private int login_id;				//로그인한 사용자 MEM_ID
	
	
	private AccountDao( ){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	//싱글톤 실험.
	
	//인스턴스 생성
	private static AccountDao instance = new AccountDao();
	
	//Get 인스턴스
	public static AccountDao getInstance() {
		if(instance==null){
			instance=new AccountDao();
		}
		return instance;
	}
	
	//Login_id 전달.
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	
	
	
	String sql = null;
	String memId = null;
	
	//0.이메일을 넣으면 mem_id반환하는메서드
	
	//1.계정조회
	//아이디값을 조회해서 true false를 반환한다.
	
	
	
	//계정 유효성검사.
	public Boolean 	validationService(String mem_eamil,String mem_pw){
		//id,pw를   확인한다.
		//입력받은 pw가 login한 이메일의 pw와  같아야한다.	->쿼리문 수정해야함... login_id기준으로
		//mail은 변경가능한지 재확인...
			
		boolean validation=true;
			
			try{
					sql="select * FROM member WHERE mem_id ='"+login_id+
						"' and mem_pw='"+ mem_pw +"' ";	
					
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = pstmt.executeQuery();

					rs.last(); // 총 레코드 수구하기
					int totalRecord = rs.getRow();
					rs.beforeFirst(); // 커서를 다시 원상태로 복귀해서 자료를 읽을 준비를 해줌

					if(totalRecord==0){
						validation=false;
					}
					
				while(rs.next()){
					memId= rs.getString("mem_id");
				}
			}
			catch(Exception err){
				System.out.println("getMemId()에서 오류");
				err.printStackTrace();
			}
			finally{
				pool.freeConnection(con, pstmt, rs);
			}
			
			
			return validation;
	}
	
	
	
		
	//2.계정삭제
	public void deleteAccount( ){
		String sql = "delete from MEMBER where MEM_ID=?";
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, this.login_id);
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("deleteAccount()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt);
		}
	}


	public void alterAccount(int newPw){
		System.out.println("비밀번호 변경 완료.");
		
/*
		try{
			String sql = "update memeber set mem_pw ='"+String.valueOf(newPw)+"'"; 
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("alterAccount()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt);
		}
	*/
		
	}
	
}
	
		
