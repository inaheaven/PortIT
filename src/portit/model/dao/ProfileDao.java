
package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;

public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;

	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();

		} catch (Exception e) {
			System.out.println("DB 접속 오류 :");
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
			System.out.println("DB 접속 오류 :");
			e.printStackTrace();
		}
	}

	/**
	 * DB 접속 해제
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(conn, stmt, rs);
			if (conn != null) {
				System.out.println("DB 접속 해제");
			}
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 :");
			e.printStackTrace();
		}
	}
	

	/**
	 * 프로필 등록(프로필 테이블 + 태크 테이블에 있는 정보 등록)
	 */
	public Profile addprofile(Profile dto, int mem_id) {
		try {

			sql = "insert into profile(prof_id, mem_id, prof_img, prof_background, prof_name, prof_nick, prof_intro, prof_website, prof_facebook, prof_github, prof_regdate, prof_follower) "
					+ "values(prof_id.nextVal, ? , ? , ? , ? , ? , ? , ?, ?, ? , sysdate, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setString(2, dto.getProf_img());
			stmt.setString(3, dto.getProf_background());
			stmt.setString(4, dto.getProf_name());
			stmt.setString(5, dto.getProf_nick());
			stmt.setString(6, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());
			stmt.setString(8, dto.getProf_facebook());
			stmt.setString(9, dto.getProf_github());
			stmt.setInt(10, dto.getProf_follower());

			stmt.executeUpdate();

			// 태그 테이블 입력(언어)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'language',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			int req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			int req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();
			
			
			
			// 태그 테이블 입력(툴)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'tool',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name2());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();

			
			// 태그 테이블 입력(분야)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'분야(field)',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name3());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();
			
			// 태그 테이블 입력(skill)(첫번째)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'skill',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name4());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력(태그 스킬  점수 입력)
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);
			stmt.setInt(3, dto.getProf_skill_level());

			stmt.executeUpdate();
			
			
			// 태그 테이블 입력(skill)(두번째)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'skill',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name5());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");
			
			//두번째 스킬 점수 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? , ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);
			stmt.setInt(3, dto.getProf_skill_level2());
			
			stmt.executeUpdate();
			
			
			// 태그 테이블 입력(skill)(세번째)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'skill',?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name6());
			
			stmt.executeUpdate();
			
			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			rs.next();
			req_prof_id = rs.getInt("prof_id");
			
			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			rs.next();
			req_tag_id = rs.getInt("tag_id");
			
			//세번째 스킬 점수 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? , ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);
			stmt.setInt(3, dto.getProf_skill_level3());
			
			stmt.executeUpdate();
		}

		catch (Exception err) {
			System.out.println("addprofile에서 오류 " + err);
		} finally {
			freeConnection();
		}
		return dto;
	}

	/**
	 *	프로필 수정 
	 */
	public void updateProfile(Profile dto, int mem_id) {

		sql = "update profile set prof_img=?, prof_background=?, "
				+ "prof_name=?, prof_nick=?, prof_intro=?, prof_website=?, prof_skill_level=?"
				+ " where mem_id = " + mem_id;

		try {
			
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getProf_website());
			stmt.setInt(7, dto.getProf_skill_level());
			/*
			stmt.setString(8, dto.getProf_language());
			stmt.setString(9, dto.getProf_tool());
			stmt.setString(10, dto.getProf_field());
			stmt.setString(11, dto.getProf_github());
			stmt.setString(12, dto.getProf_facebook());
			stmt.setInt(13, dto.getProf_follower());
			stmt.setString(14, dto.getTag_name());
			*/
			stmt.executeUpdate();
			
		} catch (Exception err) {
			System.out.println("updateProfile : " + err);
		} finally {
			freeConnection();
		}

	}
	
	/**
	 *	프로필 리스트
	 */
	public Profile getProfile(int mem_id) {
		Profile dto = new Profile();
		sql = "select * from tag join tag_use on tag.tag_id = tag_use.tag_id "
				+ "join profile on tag_use_type_id = profile.prof_id "
				+ "where mem_id="+mem_id+ " order by tag.tag_id desc";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			rs.next();
				
			dto.setMem_id(rs.getInt("mem_id"));
			dto.setProf_id(rs.getInt("prof_id"));
			dto.setProf_nick(rs.getString("prof_nick"));
			dto.setProf_name(rs.getString("prof_name"));
			dto.setProf_intro(rs.getString("prof_intro"));
			dto.setProf_img(rs.getString("prof_img"));
			dto.setProf_background(rs.getString("prof_background"));
			dto.setProf_website(rs.getString("prof_website"));
			dto.setProf_github(rs.getString("prof_github"));
			dto.setProf_facebook(rs.getString("prof_facebook"));
			
			
			dto.setProf_skill_level3(rs.getInt("prof_skill_level"));		
			dto.setTag_name6(rs.getString("tag_name"));
			rs.next();
			dto.setProf_skill_level2(rs.getInt("prof_skill_level"));		
			dto.setTag_name5(rs.getString("tag_name"));
			rs.next();
			dto.setProf_skill_level(rs.getInt("prof_skill_level"));
			dto.setTag_name4(rs.getString("tag_name"));
			rs.next();
			dto.setTag_name3(rs.getString("tag_name"));
			rs.next();
			dto.setTag_name2(rs.getString("Tag_name"));
			rs.next();
			dto.setTag_name(rs.getString("Tag_name"));
 
			
		} 
		catch (Exception err) {
			System.out.println("getProfile 오류: " + err);
		} finally {
			freeConnection();
		}
		
		return dto;
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
