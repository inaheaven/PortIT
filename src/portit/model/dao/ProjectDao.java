package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Project;

public class ProjectDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	public ProjectDao() {
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Connection Pool 오류"+e);
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
	//프로젝트 작성자 인증
	
	//프로젝트 상세 조회
	
	//프로젝트 전체 목록

	//프로젝트 등록
	public void insertPro(int PROJ_ID, String PROJ_TITLE, String PROJ_INTRO, int PROJ_TO, Date PROJ_REGDATE, Date PROJ_STARTDATE, Date PROJ_PERIOD, Date PROJ_REGENDDATE){
		Project project = new Project();
		String sql = "INSERT INTO PROJECT "
				+ "(PROJ_ID, PROJ_TITLE, PROJ_INTRO, PROJ_TO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE) "
				+ "VALUES(?, ?, ?, ?, 'sysdate', ?, ?, ?)";
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, project.getProj_id());
			stmt.setString(2, project.getProj_title());
			stmt.setString(3, project.getProj_intro());
			stmt.setInt(4, project.getProj_to());
			stmt.setDate(5, (java.sql.Date)project.getProj_startdate());
			stmt.setInt(6, project.getProj_period());
			stmt.setDate(7, (java.sql.Date) project.getProj_regenddate());
			rs = stmt.executeQuery();
			
		}catch (Exception e) {
			System.out.println("insertProj()오류 " + e);
		}
	}
	//프로젝트 수정
	public void updateProj(Connection conn) {
		getConnection();
		try {
			Project project = new Project();
			String sql = "UPDATE PROJECT "
					+ "set PROJ_TITLE=?, PROJ_INTRO=?,"
					+ " PROJ_TO=?, PROJ_REGDATE=sysdate,"
					+ "PROJ_STARTDATE=?, PROJ_PERIOD=?,PROJ_REGENDDATE=?"
					+ " where PROJ_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project.getProj_title());
			stmt.setString(2, project.getProj_intro());
			stmt.setInt(3, project.getProj_to());
			stmt.setDate(4, (java.sql.Date)project.getProj_startdate());
			stmt.setInt(5, project.getProj_period());
			stmt.setDate(6, (java.sql.Date) project.getProj_regenddate());
			stmt.setInt(7, project.getProj_id());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateProj()에서 오류"+e);
		} finally {
			freeConnection();
		}
	}
}
