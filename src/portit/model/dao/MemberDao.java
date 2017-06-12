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
	
	/**
	 * 같은 회원 번호를 쓰는 회원이 있는지 검사
	 * @param mem_id
	 * @param mem_email
	 */
	public Member findExistingMember(int mem_id, String mem_email) {
		Member member = null;
		try {
			sql = "";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMem_id());
			stmt.setString(2, member.getMem_email());
			rs = stmt.executeQuery();
			while(rs.next()) {
				member = new Member();
				// 결과를 DTO에 저장
				member.setMem_id(rs.getInt("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_regdate(rs.getDate("mem_regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 접속 해제
			freeConnection();
		}

		return member;
	}
	
	/**
	 * 회원 추가
	 * @param member
	 * @return
	 */
	public void insert(Member member) {
		try {
			sql = "";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMem_id());
			stmt.setString(2, member.getMem_email());
			stmt.setString(3, member.getMem_password());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 접속 해제
			freeConnection();
		}
	}

}
