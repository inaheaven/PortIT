package portit.model.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Member;
import portit.model.dto.Profile;

/**
 * 회원 관련 DAO
 * @author 
 *
 */
public class MemberDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	public MemberDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - MemberDao()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	public void insert(Member member) {
		try {
			conn = pool.getConnection();
			sql = "INSERT INTO MEMBER(mem_id, mem_email, mem_pw, mem_regdate) VALUES(seq_mem_id.nextVal, ?, ?, sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMem_email());
			stmt.setString(2, member.getMem_password());
			stmt.executeUpdate();
			
			// mem_id 가져오기
			sql = "SELECT mem_id FROM member WHERE mem_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMem_email());
			rs = stmt.executeQuery();
			int v_mem_id = 0;
			while(rs.next()) {	
				v_mem_id = rs.getInt("mem_id");			
			}
			
			// 멤버가 가입하면, profile 테이블에 prof_id, mem_id 값만 넣어지도록 한다.
			sql = "INSERT INTO PROFILE(prof_id, mem_id) VALUES(seq_prof_id.nextVal, ?)";
			stmt = conn.prepareStatement(sql); 
			stmt.setInt(1, v_mem_id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}
	
	/**
	 * 회원 조회 - 로그인할 때, 회원가입할 때 사용
	 * @param member
	 * @return
	 */
	public Member search(Member member) {
		int id;
		Member checkMem = new Member();
		try {
			conn = pool.getConnection();
			sql = "SELECT * FROM MEMBER WHERE mem_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMem_email());
			rs = stmt.executeQuery();
			
			if(rs.next()) {	
				checkMem.setMem_id(rs.getInt("mem_id"));
				checkMem.setMem_email(rs.getString("mem_email"));
				checkMem.setMem_password(rs.getString("mem_pw"));				
			}			
			else {
				checkMem.setMem_id(0);
//				member.setMem_email("");
//				member.setMem_password("");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}

		return checkMem;
	}
	
	
	

	
	
	
	
	public  Profile getUserInformation(int mem_id){
		
		
		String sql = null;
		String name = null;
		Profile mem_profile=null;
		try{
			
			//쿼리확인
			
			
			//페이지에서 이름혹은 메일이 출력되어야합니다.
			//이건 view에서 조건문으로 처리하도록합니다.
			
			//  이메일,mem_id, 프로필이미지경로,이름이  출력되어야합니다.
			
				sql="select distinct profile.mem_id ,profile.PROF_NAME, MEMBER.MEM_EMAIL, PROFILE.PROF_IMG  "+
					"FROM MEMBER INNER JOIN	PROFILE "+
					"on MEMBER.MEM_ID=profile.MEM_ID "+
					"where MEMBER.MEM_ID="+mem_id;
				
				conn = pool.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
			
				
			while(rs.next()){
				 mem_profile= new Profile();
				//name= rs.getString("profile.prof_name");
				mem_profile.setMem_id(rs.getInt("mem_id"));
				mem_profile.setProf_img(rs.getString("PROF_IMG"));
				mem_profile.setProf_name(rs.getString("prof_name"));
				mem_profile.setProf_email(rs.getString("MEM_EMAIL"));
			}
	
		}
		catch(Exception err){
			System.out.println("getUserInformation()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(conn, stmt, rs);
		}
		
		return mem_profile;
}

	
	
	
	
	/**
	 * 회원 탈퇴
	 */

}