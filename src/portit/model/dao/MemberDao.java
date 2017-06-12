package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Member;

/**
 * 회원 관련 DAO
 * @author gnsngck
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
	
	public void insert(Member member) {
		// DB 접속
		getConnection();
		try {
			conn = pool.getConnection();
			sql = "";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMem_id());
			stmt.setString(2, member.getMem_email());
			stmt.setString(3, member.getMem_password());
			stmt.setString(4, member.getMem_date());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 접속 해제
			freeConnection();
		}
	}

}
