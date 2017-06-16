package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;
import portit.model.dto.Tag;
import portit.model.dto.TagUse;


/**
 * 프로필 관련 DAO
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
	 * 같은 프로필 번호를 쓰는 프로필이 있는지 검사
	 * @param prof_id
	 * @return 존재하는 프로필 번호
	 */
	public int findExistingProfile(int prof_id) {
		int result = 0;
		try {
			sql = "SELECT prof_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			rs.next();
			if (prof_id == rs.getInt(1)) {
				result = rs.getInt(1);
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * (수정·삭제시) 작성자가 맞는지 검사
	 * @param mem_id
	 * @param prof_id
	 * @return true/false
	 */
	public boolean checkAccessRight(int mem_id, int prof_id) {
		boolean flag = false;
		try {
			sql = "SELECT mem_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			rs.next();
			if(mem_id == rs.getInt(1)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 프로필 입력 
	 * @param _profile
	 * @return 
	 * @return
	 */
	public Profile addprofile(Profile dto,int mem_id) {
		String sql = "insert into profile() "
				+	"values(seq_prof_id.nextVal,mem_id,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

		try{
				conn = pool.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getProf_img());
				stmt.setString(2, dto.getProf_background());
				stmt.setString(3, dto.getProf_name());
				stmt.setString(4, dto.getProf_nick());
				stmt.setString(5, dto.getProf_intro());
				stmt.setString(7, dto.getProf_website());
				stmt.setString(8, dto.getProf_github());
				stmt.setString(9, dto.getProf_facebook());
				stmt.setInt(10, dto.getProf_follower());
				stmt.setString(11, dto.getTag_name());
				stmt.setString(12, dto.getTag_name());
				stmt.setInt(13, dto.getProf_skill_level());
				stmt.executeUpdate();
			}
			catch(Exception err){
				System.out.println("profile() : " + err);
			}
			finally{
				freeConnection();
		}
			return dto;
	}
	/**
	 * 프로필 수정  
	 * 입력했던 것을 불러온다.
	 * @param profile
	 */
	public Profile getProfile(int prof_id){
		String sql="select * from profile where prof_id =?";
		
		Profile dto = new Profile();
		
		try{
			conn = pool.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  prof_id);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				dto.setProf_id(prof_id);
				dto.setProf_img(rs.getString("Prof_img"));
				dto.setProf_background(rs.getString("Prof_background"));
				dto.setProf_name(rs.getString("Prof_name"));
				dto.setProf_nick(rs.getString("Prof_nick"));
				dto.setProf_intro(rs.getString("Prof_intro"));
				dto.setProf_website(rs.getString("Prof_website"));				
				dto.setProf_github(rs.getString("Prof_github"));				
				dto.setProf_facebook(rs.getString("Prof_facebook"));				
				dto.setProf_follower(rs.getInt("Prof_follower"));				
				dto.setTag_name(rs.getString("Tag_name"));				
				dto.setTag_name(rs.getString("Tag_name"));				
				dto.setProf_skill_level(rs.getInt("Prof_skill_level"));				
			}
		}
		catch(Exception err){
			System.out.println("profile() : " + err);
		}
		finally{
			freeConnection();
		}
		return dto;
		
	}
	/**
	 * 프로필 수정 란(내용값 수정)
	 * @param dto
	 */
	
	public void updateProfile(Profile dto){
		
		String sql = "update profile set prof_img=?, prof_background=?, prof_name=?, prof_nick=?, prof_intro=?"
				+ ", prof_img=?, prof_background=?, prof_website=?, prof_github=?, prof_facebook=?, prof_facebook=?, prof_regdate"
				+ ", prof_follower=?, Tag_name=?, Tag_name=?, Prof_skill_level=? where prof_id=?";
		
		
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());
			stmt.setString(8, dto.getProf_github());
			stmt.setString(9, dto.getProf_facebook());
			stmt.setInt(10, dto.getProf_follower());
			stmt.setString(11, dto.getTag_name());
			stmt.setString(12, dto.getTag_name());
			stmt.setInt(13, dto.getProf_skill_level());
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP 연결 실패 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
	
/**
 * 프로필 삭제 
 * @param eno
 */
	
	// deleteEmp_proc.jsp
	public void deleteProfile(int prof_id){
		String sql = "delete from profile where prof_id ="+prof_id+"";
		
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