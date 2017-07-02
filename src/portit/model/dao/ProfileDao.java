package portit.model.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	public Profile addprofile(Profile dto) {
		String sql = "INSERT into profile"
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
				
				//list.add(dto);
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
	
	/**
	 * 상세페이지를 위한 전체 select + dto에 저장
	 */
	public Profile selectForDetail(int mem_id) {
		Profile pf = new Profile();
		try {
			conn = pool.getConnection();
			
			sql = "SELECT * FROM profile WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				pf.setProf_id(rs.getInt("prof_id"));
				pf.setMem_id(rs.getInt("mem_id"));
				pf.setProf_nick(rs.getString("prof_nick"));
				pf.setProf_name(rs.getString("prof_name"));
				pf.setProf_intro(rs.getString("prof_intro"));
				pf.setProf_img(rs.getString("prof_img"));
				pf.setProf_background(rs.getString("prof_background"));
				pf.setProf_website(rs.getString("prof_website"));
				pf.setProf_github(rs.getString("prof_github"));
				pf.setProf_facebook(rs.getString("prof_facebook"));
				pf.setProf_regdate(rs.getDate("prf_regdate"));
				pf.setProf_follower(rs.getInt("prof_follower"));
				
				getTag(pf, rs.getInt(mem_id));
				
//				private String prof_language;
//				private String prof_tool;
//				private String prof_field;
//				private String tag_name;
//				private int prof_skill_level;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
		
		return pf;
	}
	
	/**
	 *  profile에서 쓴 태그 
	 */
	public void getTag(Profile pf, int mem_id) {
		try{
			sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ "WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' AND tu.tag_use_type_id = ? "
					+ "ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 4";  // 랜덤하게 3개
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();			
			while(rs2.next()){	
				tags.add(rs.getString("tag_name"));
			}
			pf.setTags(tags);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * profile 의 portfolio
	 * 
	 */

	
	/**
	 * profile 의 project
	 */
}
