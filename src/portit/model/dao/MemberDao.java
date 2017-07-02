package portit.model.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Member;

/**
 * 회원 관련 DAO
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
	 * 회원번호로 특정 회원 조회
	 * @param mem_id
	 * @return
	 */
	public Member selectOneById(int mem_id) {
		Member member = new Member();
		getConnection();
		try {
			sql = "SELECT * FROM member WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				member.setMem_id(rs.getInt("mem_id"))
				.setMem_password(rs.getString("mem_pw"))
				.setMem_email(rs.getString("mem_email"))
				.setMem_regdate(rs.getDate("mem_regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return member;
	}
	
	/**
	 * 이메일로 특정 회원 조회
	 * @param mem_email
	 * @return
	 */
	public Member selectOneByEmail(String mem_email) {
		Member member = new Member();
		getConnection();
		try {
			sql = "SELECT * FROM member WHERE mem_email=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mem_email);
			rs = stmt.executeQuery();
			while (rs.next()) {
				member.setMem_id(rs.getInt("mem_id"))
				.setMem_password(rs.getString("mem_pw"))
				.setMem_email(rs.getString("mem_email"))
				.setMem_regdate(rs.getDate("mem_regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return member;
	}
	
	public Member selectOneByLogin(String mem_email, String mem_password) {
		Member member = new Member();
		getConnection();
		try {
			sql = "SELECT * FROM member WHERE mem_email=? AND mem_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mem_email);
			stmt.setString(2, mem_password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				member.setMem_id(rs.getInt("mem_id"))
				.setMem_password(rs.getString("mem_pw"))
				.setMem_email(rs.getString("mem_email"))
				.setMem_regdate(rs.getDate("mem_regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return member;
	}
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	public int insert(Member member) {
		int rows = 0;
		getConnection();
		try {
			sql = "INSERT INTO MEMBER(mem_id, mem_email, mem_pw, mem_regdate)"
					+ " VALUES(LPAD(seq_mem_id.nextVal, 4, '0'), ?, ?, sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMem_email());
			stmt.setString(2, member.getMem_password());
			rows += stmt.executeUpdate();
			
			/*// 멤버가 가입하면, profile 테이블에 prof_id, mem_id 값만 넣어지도록 한다.
			sql = "INSERT INTO PROFILE(prof_id, mem_id) VALUES(seq_prof_id.nextVal, ?)";
			stmt = conn.prepareStatement(sql); 
			stmt.setInt(1, v_mem_id);
			stmt.executeUpdate();*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
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
			sql = "SELECT * FROM MEMBER WHERE mem_email=?";
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
	
	/**
	 * 회원 수정
	 * @param mem_id
	 * @param member
	 * @return
	 */
	public int update(int mem_id, Member member) {
		int rows = 0;
		getConnection();
		try {
			sql = "UPDATE member SET mem_email=?, mem_pw=? WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMem_email());
			stmt.setString(1, member.getMem_password());
			stmt.setInt(3, mem_id);
			rows += stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	/**
	 * 회원 삭제
	 * @param mem_id
	 * @return
	 */
	public int delete(int mem_id) {
		int rows = 0;
		getConnection();
		try {
			// 회원 정보 삭제
			sql = "DELETE member WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rows += stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}

}