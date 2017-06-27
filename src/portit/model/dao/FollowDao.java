package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Follow;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * Follow관련 Dao
 * @author hyang
 *
 */
public class FollowDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	public FollowDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - FollowDao()");
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
	 *  follow 추가
	 * @param fw_id
	 * @return
	 */
	public Profile getFollow(int prof_id) {
		String sql = "SELECT * FROM PROFILE WHERE prof_id=?";
		//String sql = "";

		Profile profile = new Profile();
		//Tag dto1 = new Tag(); 
		
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				profile.setProf_img(rs.getString("prof_img"));//프로필사진
				profile.setProf_name(rs.getString("prof_name"));//개발자이름
			}
			
			// DB에서 Follower 수 조회
			sql = "SELECT COUNT(*) FROM FOLLOWING WHERE mem_id_receiver=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, profile.getProf_id());
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				profile.setProf_follower(rs.getInt(1));
			}
			
			// DB에서 태그 사용 테이블 조회
			
			sql = "SELECT * FROM TAGUSE tu "
					+ "INNER JOIN TAG t "
					+ "ON tu.tag_id=t.tag_id "
					+ "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "profile");
			stmt.setInt(2, profile.getProf_id());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				if (rs.getString("tag_type").equals("tool")) {
					profile.setProf_language(new Tag()
							.setTag_id(rs.getInt("t.tag_id"))
							.setTag_type(rs.getString("t.tag_type"))
							.setTag_name(rs.getString("t.tag_name")));
			
		}
			}
			
		}catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		
	
		
		return profile;
	}

	/**
	 *  following 삭제
	 * @param fw_id
	 */
	public void deleteFollow(int fw_id) {
		String sql = "delete from following where fw_id ="+fw_id+"";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP 연결 실패 : " + err);
		} finally {
			freeConnection();
		}

	}
	
}
