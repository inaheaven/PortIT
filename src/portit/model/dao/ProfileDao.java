package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;

	/**
	 * 프로필 등록 DAO
	 * @author hyang
	 *
	 */
	public class ProfileDao {

		private Connection conn;
		private PreparedStatement stmt;
		private ResultSet rs;
		private DBConnectionMgr pool;
		private String sql = null;
		
		public ProfileDao() {
			try {
				pool = DBConnectionMgr.getInstance();
			} catch (Exception e) {
				System.out.println("커넥션 풀 오류 - MemberDao()");
				e.printStackTrace();
			}
		}
		
		/**
		 * DB 연결
		 */
		private void getConnection() {
			try {
				conn = pool.getConnection();
				if (conn != null) System.out.println("DB 접속");
			} catch (Exception e) {
				System.out.println("DB 접속 오류 - getConnection()");
				e.printStackTrace();
			}
		}
		
		/**
		 * DB 연결 해제
		 */
		private void freeConnection() {
			try {
				pool.freeConnection(conn, stmt, rs);
				if (conn != null) System.out.println("DB 접속 해제");
			} catch (Exception e) {
				System.out.println("DB 접속해제 오류 - freeConnection()");
				e.printStackTrace();
			}
		}
/**
 * 
 * @param dto
 */
	public void Profile(){
		String sql = "insert into () "
				+	"values(,?,?,?,?,?,?)";//프로필 작성시sql쿼리
		
		Profile dto = new Profile();
	
		
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getTag_name());  
			stmt.setString(7, dto.getProf_website());  
			stmt.setString(8, dto.getProf_github());  
			stmt.setString(9, dto.getProf_facebook());  
			stmt.setString(10, dto.getProf_facebook());  
			stmt.setString(11, dto.getTag_name());  
			stmt.setInt(11, dto.getProf_skill_level());  
			
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("profile() : " + err);
		}
		finally{
			freeConnection();
		}
	}
	
	
	public void deleteProfile(int fw_id){
		String sql = "delete from where = ";
		
		try{
			conn = pool.getConnection();	
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP 연결 실패 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
}
	
	