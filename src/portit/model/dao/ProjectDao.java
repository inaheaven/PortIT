package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.fmt.ParseDateSupport;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Project;

public class ProjectDao {
	
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
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
	public void insertPro(HttpServletRequest req, HttpServletResponse resp){
	
		String proj_title = req.getParameter("proj_title");
		String proj_intro = req.getParameter("proj_intro");
		int proj_to = Integer.parseInt(req.getParameter("proj_to"));
		String proj_startdate =  req.getParameter("proj_startdate");
		int proj_period = Integer.parseInt(req.getParameter("proj_period"));
		String proj_regenddate =  req.getParameter("proj_regenddate");
		
	
		try {
			//프로젝트 시작일에 값을 받아와서 sql문에 입력가능한 형태의 문자열로 재변환
			Date proj_startdate_format = new SimpleDateFormat("MM/dd/yyyy").parse(proj_startdate);
			Date proj_regendtdate_format = new SimpleDateFormat("MM/dd/yyyy").parse(proj_regenddate);
			proj_startdate = new SimpleDateFormat("yyyy-MM-dd").format(proj_startdate_format);
			proj_regenddate = new SimpleDateFormat("yyyy-MM-dd").format(proj_regendtdate_format);
		} catch (ParseException e) {
			System.out.println("날짜 변경 오류"+e);
		}
		
		
		String sql = "INSERT INTO PROJECT "
				+ "(PROJ_ID, PROJ_TITLE, PROJ_INTRO, PROJ_TO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE) "
				+ "VALUES(mypage_proj_id.nextVal, ?, ?, ?, sysdate, ?, ?, ?)";
	
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, proj_title);		//프로젝트 제목
			stmt.setString(2, proj_intro);		//프로젝트 설명
			stmt.setInt(3, proj_to);					//프로젝트 모집 인원 수
			stmt.setString(4, proj_startdate);	//프로젝트 시작일
			stmt.setInt(5, proj_period);					//프로젝트 기간
			stmt.setString(6, proj_regenddate);	//프로젝트 마감일
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
