package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Follow;

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
	 *  follow 추가
	 * @param fw_id
	 * @return
	 */
	public Follow FollowDao(int fw_id) {
		String sql = "";
		Follow dto = new Follow();
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fw_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setFw_id(rs.getInt("fw_id"));
				dto.setMem_id_sender(rs.getInt("mem_id_sender"));
				dto.setMem_id_receiver(rs.getInt("mem_id_receiver"));
				dto.setFw_date(rs.getDate("fw_date"));
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
