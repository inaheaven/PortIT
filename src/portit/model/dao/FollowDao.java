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
		String sql = "";
		//String sql = "";

		Profile dto = new Profile();
		//Tag dto1 = new Tag(); 
		
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setProf_img(rs.getString("prof_img"));//프로필사진
				dto.setProf_name(rs.getString("prof_name"));//개발자이름
				dto.setProf_follower(rs.getInt("prof_follower"));//팔로워수
			//	
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		
		
		//개발자가 입력한 태그 불러오기 
		String sql1 = "";
		
		Tag dto1 = new Tag(); 
		
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				dto1.setTag_name(rs.getString("tag_name"));//태그3개까지만 개발자 기술태그
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		
		
		return dto;
	}

	/**
	 *  following 삭제
	 * @param fw_id
	 */
	public void FollowDelete(int fw_id) {
		String sql = "";

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
