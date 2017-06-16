package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Project;

public class ProjectDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;

	public ProjectDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("Connection Pool 오류" + e);
		}
	}

	/**
	 * DB 연결
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null)
				System.out.println("DB 접속");
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
			if (conn != null)
				System.out.println("DB 접속 해제");
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 - freeConnection()");
			e.printStackTrace();
		}
	}
	// 프로젝트 작성자 인증

	// 프로젝트 상세 조회

	// 프로젝트 전체 목록

	// 프로젝트 등록
	public void reg_pro(HttpServletRequest req, HttpServletResponse resp) {

		String proj_title = req.getParameter("proj_title"); // 프로젝트 제목 
		String proj_intro = req.getParameter("proj_intro"); // 프로젝트 내용 
		String proj_startdate = req.getParameter("proj_startdate"); // 프로젝트 시작일	
		int proj_period = Integer.parseInt(req.getParameter("proj_period")); // 프로젝트	
		String proj_regenddate = req.getParameter("proj_regenddate"); // 프로젝트 등록마감일

		// 프로젝트 시작일과 등록마감일에 값의 포맷을 변경하여 sql문에 입력가능한 형태의 문자열로 재변환. 파싱 이용시 예외처리
		try {
			Date proj_startdate_format = new SimpleDateFormat("MM/dd/yyyy").parse(proj_startdate);
			Date proj_regendtdate_format = new SimpleDateFormat("MM/dd/yyyy").parse(proj_regenddate);
			proj_startdate = new SimpleDateFormat("yyyy-MM-dd").format(proj_startdate_format);
			proj_regenddate = new SimpleDateFormat("yyyy-MM-dd").format(proj_regendtdate_format);
		} catch (ParseException e) {
			System.out.println("날짜 변경 오류" + e);
		}

		try {
			// 폼에 입력받은 데이터를 DB 입력을 위한 쿼리문 1
			String sql = "INSERT INTO PROJECT "
					+ "(PROJ_ID, PROJ_TITLE, PROJ_INTRO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE) "
					+ "VALUES(mypage_proj_id.nextVal, ?, ?, sysdate, ?, ?, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setString(3, proj_startdate); // 프로젝트 시작일
			stmt.setInt(4, proj_period); // 프로젝트 기간
			stmt.setString(5, proj_regenddate); // 프로젝트 마감일
			rs = stmt.executeQuery();
			
			// 폼에 입력받은 환경 데이터를 TAG_USE 테이블에 입력을 위한 변수 설정
			String tag_use_type = "PROJ_REG";
			// 자동 입력된 PROJ_ID를 PROJECT 테이블에서 호출 - tag_use_type_id 는 글번호
			String sql_tag_use_type_id = "SELECT PROJ_ID FROM PROJECT WHERE PROJ_INTRO='" + proj_intro + "'";
			stmt = conn.prepareStatement(sql_tag_use_type_id);
			rs = stmt.executeQuery();
			rs.next();
			int tag_use_type_id = rs.getInt("PROJ_ID");	//테그에 사용하는 글번호 - 프로젝트 번호
			int tag_id = 0;

			
			// 프로젝트 개발 황경을 TAG, TAG_USE 테이블에 입력
			// 프로젝트 개발 환경에 입력된 내용 호출 및 분리
			StringTokenizer proj_env_st = new StringTokenizer(req.getParameter("proj_env"), ",/^&*");
			while (proj_env_st.hasMoreTokens()) {
				// 폼에 입력받은 환경 데이터를 TAG 테이블에 입력을 위한 쿼리문
				String tag_type = "env";
				String tag_name = proj_env_st.nextToken().trim();

				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				// 자동 입력된 TAG_ID를 TAG 테이블에서 호출
				String sql_tag_id = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE='env' AND TAG_NAME='" + tag_name + "'";
				stmt = conn.prepareStatement(sql_tag_id);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");				//전역변수로 tag_id를 미리설정해 놓았기 때문에 다른 토큰 지역에서 별도의 설정 불필요	

				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			}

			// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
			StringTokenizer proj_language_st = new StringTokenizer(req.getParameter("proj_language"), ",/^&* ");
			while (proj_language_st.hasMoreTokens()) {
				String tag_type = "language";
				String tag_name = proj_language_st.nextToken().trim();
				// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			}

			// 프로젝트 개발 도구에 입력된 내용 호출 및 분리
			StringTokenizer proj_tool_st = new StringTokenizer(req.getParameter("proj_tool"), ",/^&*");
			while (proj_tool_st.hasMoreTokens()) {
				String tag_type = "tool";
				String tag_name = proj_tool_st.nextToken().trim();

				// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				
				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			}

			// 프로젝트 모집 분야 및 인원에 입력된 내용 호출 및 분리
			String[] proj_fields = req.getParameterValues("proj_field");				//다수의 모집분야 배열 호출
			String[] proj_numofperson_arr = req.getParameterValues("proj_numofperson"); //다수의 모집인원 배열 호출, 이후 사용시 정수형 변환
			for (int i = 0; i < proj_fields.length; i++) {
				StringTokenizer proj_field_st = new StringTokenizer(proj_fields[i], ",/^&*");
				while (proj_field_st.hasMoreTokens()) {
					String tag_type = "field";
					String tag_name = proj_field_st.nextToken().trim();
					int proj_numofperson =  Integer.parseInt(proj_numofperson_arr[i]);	//정수형 변환
					
					// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
					sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(mypage_proj_tag_id.nextVal, ?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();
					
					sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(mypage_proj_tag_use_id.nextVal, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.setInt(4, proj_numofperson);
					rs = stmt.executeQuery();
				}
				
			}

		} catch (Exception e) {
			System.out.println("insertProj()오류 " + e);
		}

	}

	// 프로젝트 수정
	public void updateProj(Connection conn) {
		getConnection();
		try {
			Project project = new Project();
			String sql = "UPDATE PROJECT " + "set PROJ_TITLE=?, PROJ_INTRO=?," + "PROJ_REGDATE=sysdate,"
					+ "PROJ_STARTDATE=?, PROJ_PERIOD=?,PROJ_REGENDDATE=?" + " where PROJ_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project.getProj_title());
			stmt.setString(2, project.getProj_intro());
			stmt.setDate(3, (java.sql.Date) project.getProj_startdate());
			stmt.setInt(4, project.getProj_period());
			stmt.setDate(5, (java.sql.Date) project.getProj_regenddate());
			stmt.setInt(6, project.getProj_id());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateProj()에서 오류" + e);
		} finally {
			freeConnection();
		}
	}
}
