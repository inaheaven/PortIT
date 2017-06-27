package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Member;
import portit.model.dto.Profile;

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
	 * 회원을 이름/닉네임을 통해서 검사
	 * @param req
	 * @param resp
	 */
	public ArrayList<Profile> list = new ArrayList<>();
	public void findMemberbyName(HttpServletRequest req, HttpServletResponse resp){
		
		String coworker = req.getParameter("name");
		sql="SELECT PROF_NAME, PROF_NICK FROM PROFILE WHERE PROF_NAME=?";
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, coworker);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Profile prof = new Profile();
				String prof_name = (rs.getString("PROF_NAME"));
				String prof_nick = (rs.getString("PROF_NICK"));
				prof.setProf_name(prof_name);
				prof.setProf_nick(prof_nick);
				list.add(prof);
				System.out.println(prof_name);
				System.out.println(prof_nick);
			}
			
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("search_result.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			freeConnection();
		}
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
