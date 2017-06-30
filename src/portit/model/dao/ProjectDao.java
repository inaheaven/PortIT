package portit.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;
import portit.model.dto.Project;
import portit.model.dto.Tag;

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

	/**
	 * 프로젝트 수정을 위한 해당 값 불러오기
	 * 
	 * @param req
	 * @param resp
	 */
	public void read_proj(HttpServletRequest req, HttpServletResponse resp) {

		String req_proj_id = req.getParameter("proj_id"); // 프로젝트 아이디 번호
		String proj_title = ""; // 프로젝트 테이블에서 사용할 변수
		String proj_intro = "";
		String proj_regdate = "";
		String proj_startdate = "";
		int proj_period = 0;
		String proj_regenddate = "";

		int proj_tag_id; // 테그 테이블에서 사용할 변수
		String proj_tag_name; // 테그명
		String env;
		ArrayList<String> proj_env_list = new ArrayList<>();
		String lang;
		ArrayList<String> proj_language_list = new ArrayList<>();
		String tool;
		ArrayList<String> proj_tool_list = new ArrayList<>();
		String field;
		ArrayList<String> proj_field_list = new ArrayList<>();
		int proj_numofperson = 0;
		ArrayList<Integer> numofperson_list = new ArrayList<>();
		String prof_name;
		String prof_nick;
		ArrayList<String> prof_name_list = new ArrayList<>();
		ArrayList<String> prof_nick_list = new ArrayList<>();
		String coworker;
		ArrayList<String> coworker_list = new ArrayList<>();
		int prof_id =0;
		
		// dto
		Project project = new Project();
		Tag env_tag = new Tag();
		Tag field_tag = new Tag();
		Tag lang_tag = new Tag();
		Tag tool_tag = new Tag();
		Profile profile = new Profile();

		// dto 입력후 request를 통한 전달을 위한 ArrayList
		ArrayList<Project> update_list = new ArrayList<>();
		ArrayList<Tag> env_list = new ArrayList<>();
		ArrayList<Tag> lang_list = new ArrayList<>();
		ArrayList<Tag> tool_list = new ArrayList<>();
		ArrayList<Tag> field_list = new ArrayList<>();
		ArrayList<Profile> prof_list = new ArrayList<>();

		// tag_id 분리를 위한 ArrayList
		ArrayList<Integer> tag_id_list = new ArrayList<>();
		try {
			// project 테이블에서 필요한 값 불러오기
			String proj_sql = "SELECT PROJ_TITLE, PROJ_INTRO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE FROM PROJECT WHERE PROJ_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(proj_sql);
			stmt.setString(1, req_proj_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			while (rs.next()) {
				proj_title = rs.getString("proj_title");
				proj_intro = rs.getString("proj_intro");
				proj_regdate = rs.getString("proj_regdate");
				proj_startdate = rs.getString("proj_startdate");
				proj_period = rs.getInt("proj_period");
				proj_regenddate = rs.getString("proj_regenddate");

			}
		} catch (Exception e) {
			System.out.println("project 테이블 쿼리 오류" + e);
		}

		// 검색시 활용하기 위한 회원 아이디 번호
		int mem_id = 0;

		// Member 테이블에서 필요한 값 불러오기
		try {
			String member_id_sql = "SELECT MEM_ID FROM PROJ_APP WHERE PROJ_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(member_id_sql);
			stmt.setString(1, req_proj_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			while (rs.next()) {
				rs.next();
				mem_id = rs.getInt("mem_id");
			}
			;
		} catch (Exception e) {
			System.out.println("project_app 테이블 쿼리 오류" + e);
		}

		try {
			String member_name_sql = "SELECT PROF_ID, PROF_NAME, PROF_NICK FROM PROFILE WHERE MEM_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(member_name_sql);
			stmt.setInt(1, mem_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			while (rs.next()) {
				prof_id = rs.getInt("prof_id");
				prof_name = rs.getString("prof_name");
				prof_nick = rs.getString("prof_nick");
				prof_name_list.add(prof_name);
				prof_nick_list.add(prof_nick);
				
				profile.setProf_name_list(prof_name_list);
				profile.setProf_nick_list(prof_nick_list);
				prof_list.add(profile);
			}
		} catch (Exception e) {
			System.out.println("profile 테이블 쿼리 오류");
		}

		// Tag_USE 테이블에서 필요한 값 불러오기
		try {
			String tag_sql = "SELECT TAG_ID, PROJ_NUMOFPERSON FROM TAG_USE WHERE TAG_USE_TYPE='PROJ_REG' AND TAG_USE_TYPE_ID=?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(tag_sql);
			stmt.setString(1, req_proj_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				proj_tag_id = rs.getInt("TAG_ID");
				proj_numofperson = rs.getInt("PROJ_NUMOFPERSON");
				if (rs.getInt("PROJ_NUMOFPERSON") != 0) {
					numofperson_list.add(proj_numofperson);
				}
				tag_id_list.add(proj_tag_id);
			}
		} catch (Exception e) {
			System.out.println("TAG_USE테이블 쿼리 오류" + e);
		}

		Iterator<Integer> tag = tag_id_list.iterator();
		try {
			while (tag.hasNext()) {
				proj_tag_id = tag.next();
				String tag_id_sql = "SELECT TAG_TYPE, TAG_NAME FROM TAG WHERE TAG_ID = ?";
				conn = pool.getConnection();
				stmt = conn.prepareStatement(tag_id_sql);
				stmt.setInt(1, proj_tag_id);
				rs = stmt.executeQuery();
				rs.next();
				proj_tag_name = rs.getString("TAG_NAME");
				String tag_type = rs.getString("TAG_TYPE");
				if (tag_type.equals("env")) {
					env = proj_tag_name;
					proj_env_list.add(env);
					env_tag.setProj_env_list(proj_env_list);
					env_list.add(env_tag);
				} else if (tag_type.equals("field")) {
					field = proj_tag_name;
					proj_field_list.add(field);
					field_tag.setProj_field_list(proj_field_list);
					field_list.add(field_tag);
				} else if (tag_type.equals("language")) {
					lang = proj_tag_name;
					proj_language_list.add(lang);
					lang_tag.setProj_lang_list(proj_language_list);
					lang_list.add(lang_tag);
				} else if (tag_type.equals("tool")) {
					tool = proj_tag_name;
					proj_tool_list.add(tool);
					tool_tag.setProj_tool_list(proj_tool_list);
					tool_list.add(tool_tag);
				}
			}
		} catch (Exception e) {
			System.out.println("tag 테이블에서 쿼리 오류" + e);
		} finally {
			freeConnection();
		}
		project.setProj_numofperson(numofperson_list);
		project.setProj_id(Integer.parseInt(req_proj_id));
		project.setProj_title(proj_title);
		project.setProj_intro(proj_intro);
		project.setProj_regdate(proj_regdate);
		project.setProj_startdate(proj_startdate);
		project.setProj_period(proj_period);
		project.setProj_regenddate(proj_regenddate);
		update_list.add(project);

		req.setAttribute("list", update_list);
		req.setAttribute("env_list", env_list);
		req.setAttribute("language_list", lang_list);
		req.setAttribute("tool_list", tool_list);
		req.setAttribute("field_list", field_list);
		req.setAttribute("prof_list", prof_list);

		RequestDispatcher view = req.getRequestDispatcher("myProjRegisterEdit.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 프로젝트 등록
	 * 
	 * @param req
	 * @param resp
	 */
	public void reg_pro(HttpServletRequest req, HttpServletResponse resp) {

		String proj_title = req.getParameter("proj_title"); // 프로젝트 제목
		String proj_intro = req.getParameter("proj_intro"); // 프로젝트 내용
		String proj_startdate = req.getParameter("proj_startdate"); // 프로젝트 시작일
		int proj_period = Integer.parseInt(req.getParameter("proj_period")); // 프로젝트
		String proj_regenddate = req.getParameter("proj_regenddate"); // 프로젝트
																		// 등록마감일

		try {
			// 폼에 입력받은 데이터를 프로젝트 테이블에 입력
			String sql = "INSERT INTO PROJECT "
					+ "(PROJ_ID, PROJ_TITLE, PROJ_INTRO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE) "
					+ "VALUES(seq_proj_id.nextVal, ?, ?, sysdate, ?, ?, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setString(3, proj_startdate); // 프로젝트 시작일
			stmt.setInt(4, proj_period); // 프로젝트 기간
			stmt.setString(5, proj_regenddate); // 프로젝트 마감일
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("폼에 입력받은 데이터를 프로젝트 테이블에 입력" + e);
		}

		// 폼에 입력받은 함께할 사람데이터를 proj_app 테이블에 입력
		int mem_id = 0;
		ArrayList<Integer> mem_id_list = new ArrayList<>();
		int proj_id = 0;
		String proj_coworker = req.getParameter("final_result_id");
		String[] proj_coworkers = proj_coworker.split(",");
		
		try {
			for (int i = 0; i < proj_coworkers.length; i++) {
				String sql_coworker = "SELECT MEM_ID FROM MEMBER WHERE PROF_ID = ?";
				stmt = conn.prepareStatement(sql_coworker);
				stmt.setString(1, proj_coworkers[i]);
				rs = stmt.executeQuery();
				while(rs.next()){
				mem_id_list.add(rs.getInt("mem_id"));
				}
			}
		} catch (Exception e) {
			System.out.println("멤버 테이블에서 멤버 아이디 검색" + e);
		}
		try {
			String sql_proj_id = "SELECT PROJ_ID FROM PROJECT WHERE PROJ_INTRO=? AND PROJ_TITLE=?";
			stmt = conn.prepareStatement(sql_proj_id);
			stmt.setString(1, proj_intro);
			stmt.setString(2, proj_title);
			rs = stmt.executeQuery();
			rs.next();
			proj_id = rs.getInt("PROJ_ID"); // 테그에 사용하는 글번호 - 프로젝트 번호
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}
		try {
			String insert_proj_app_sql = "INSERT INTO PROJ_APP(PROJ_APP_ID, MEM_ID, PROJ_ID, PROJ_APP_REGDATE, PROJ_APP_CONFIRM) VALUES(seq_PROJ_APP_ID.nextval, ?, ?, sysdate, 't')";
			stmt = conn.prepareStatement(insert_proj_app_sql);
			Iterator<Integer> mem_id_iter = mem_id_list.iterator();
			while (mem_id_iter.hasNext()) {
				mem_id = mem_id_iter.next();
				System.out.println(mem_id);
				stmt.setInt(1, mem_id);
				stmt.setInt(2, proj_id);
				rs = stmt.executeQuery();
			}
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}

		// 폼에 입력받은 환경 데이터를 TAG_USE 테이블에 입력을 위한 변수 설정
		String tag_use_type = "PROJ_REG"; // 프로젝트에서 등록된 글임을 표시
		int tag_use_type_id = proj_id; // 테그에 사용하는 글번호 - 프로젝트 번호
		int tag_id = 0; // tag_id를 초기화 이후 select문으로 추가

		// 프로젝트 개발 황경을 TAG, TAG_USE 테이블에 입력
		// 프로젝트 개발 환경에 입력된 내용 호출 및 분리
		StringTokenizer proj_env_st = new StringTokenizer(req.getParameter("proj_env"), ",/^&*");
		while (proj_env_st.hasMoreTokens()) {
			// 폼에 입력받은 환경 데이터를 TAG 테이블에 입력을 위한 쿼리문
			String tag_type = "env";
			String tag_name = proj_env_st.nextToken().trim();
			try {
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				// 자동 입력된 TAG_ID를 TAG 테이블에서 호출
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE='env' AND TAG_NAME='" + tag_name
						+ "'";
				stmt = conn.prepareStatement(select_tag_id_sql);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");

				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			} catch (Exception e) {
				System.out.println("TAG, TAG_USE테이블에 환경 데이터 입력" + e);
			}
		}

		// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
		StringTokenizer proj_language_st = new StringTokenizer(req.getParameter("proj_language"), ",/^&* ");
		while (proj_language_st.hasMoreTokens()) {
			String tag_type = "language";
			String tag_name = proj_language_st.nextToken().trim();
			// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
			try {
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");

				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			} catch (Exception e) {
				System.out.println("TAG, TAG_USE테이블에 언어 데이터 입력" + e);
			}
		}

		// 프로젝트 개발 도구에 입력된 내용 호출 및 분리
		StringTokenizer proj_tool_st = new StringTokenizer(req.getParameter("proj_tool"), ",/^&*");
		while (proj_tool_st.hasMoreTokens()) {
			String tag_type = "tool";
			String tag_name = proj_tool_st.nextToken().trim();
			try {
				// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("1");
			}
			try {
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");
			} catch (Exception e) {
				System.out.println("2");
			}
			try {
				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("3TAG, TAG_USE테이블에 개발 데이터 입력" + e);
			}
		}

		// 프로젝트 모집 분야 및 인원에 입력된 내용 호출 및 분리
		String[] proj_fields = req.getParameterValues("proj_field"); // 다수의 모집분야
																		// 배열 호출
		String[] proj_numofperson_arr = req.getParameterValues("proj_numofperson"); // 다수의모집인원배열호출,이후사용시정수형변환
		for (int j = 0; j < proj_fields.length; j++) {
			StringTokenizer proj_field_st = new StringTokenizer(proj_fields[j], ",/^&*");
			while (proj_field_st.hasMoreTokens()) {
				String tag_type = "field";
				String tag_name = proj_field_st.nextToken().trim();
				int proj_numofperson = Integer.parseInt(proj_numofperson_arr[j]); // 정수형
																					// 변환
				try {
					// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
					String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();

					String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
					stmt = conn.prepareStatement(select_tag_id_sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();
					rs.next();
					tag_id = rs.getInt("tag_id");

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(seq_tag_use_id.nextVal, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.setInt(4, proj_numofperson);
					rs = stmt.executeQuery();
				} catch (Exception e) {
					System.out.println("모집분야 및 인원 테이블에 등록오류 " + e);
				}
			}
		}

	}

	// 함께할 사람등록을 위한 회원이름검색 - 회원이름 닉네임을 통해서 검사시 필요한 전역변수지정 리스트
	public ArrayList<Profile> list = new ArrayList<>();

	/**
	 * 회원을 이름/닉네임을 통해서 검사
	 * 
	 * @param req
	 * @param resp
	 */
	public void findMemberbyName(HttpServletRequest req, HttpServletResponse resp) {

		String coworker = req.getParameter("name");
		String sql = "SELECT PROF_NAME, PROF_NICK, PROF_ID FROM PROFILE WHERE PROF_NAME=?";
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, coworker);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Profile prof = new Profile();
				String prof_name = (rs.getString("PROF_NAME"));
				String prof_nick = (rs.getString("PROF_NICK"));
				int prof_id = (rs.getInt("PROF_ID"));
				prof.setProf_name(prof_name);
				prof.setProf_nick(prof_nick);
				prof.setProf_id(prof_id);
				list.add(prof);
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
	 * 프로젝트 삭제
	 * 
	 * @param req
	 * @param resp
	 */
	public void delete_pro(HttpServletRequest req, HttpServletResponse resp) {
		String proj_id = req.getParameter("proj_id");
		// PROJ_APP테이블데이터 삭제

		try {
			String sel_proj_app_sql = "SELECT PROJ_APP_ID FROM PROJ_APP WHERE PROJ_ID=?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sel_proj_app_sql);
			stmt.setString(1, proj_id);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				String del_proj_app_sql = "DELETE FROM PROJ_APP WHERE PROJ_ID = ?";
				try {
					stmt = conn.prepareStatement(del_proj_app_sql);
					stmt.setString(1, proj_id);
					rs = stmt.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// TAG아이디 값 호출
		String tag_sql = "SELECT TAG_ID FROM TAG_USE WHERE TAG_USE_TYPE='PROJ_REG' AND TAG_USE_TYPE_ID=?";
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(tag_sql);
			stmt.setString(1, proj_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int proj_tag_id = rs.getInt("TAG_ID");
				// TAG_USE테이블데이터 삭제
				String del_tag_use_sql = "DELETE FROM TAG_USE WHERE TAG_ID = ?";
				stmt = conn.prepareStatement(del_tag_use_sql);
				stmt.setInt(1, proj_tag_id);
				rs = stmt.executeQuery();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String proj_del_sql = "DELETE FROM PROJECT WHERE PROJ_ID = ?";
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(proj_del_sql);
			stmt.setString(1, proj_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 프로젝트 수정
	 * 
	 * @param req
	 * @param resp
	 */
	public void update_proj(HttpServletRequest req, HttpServletResponse resp) {
		String req_proj_id = req.getParameter("final_result_id");
		String proj_title = req.getParameter("proj_title"); // 프로젝트 제목
		String proj_intro = req.getParameter("proj_intro"); // 프로젝트 내용
		String proj_startdate = req.getParameter("proj_startdate"); // 프로젝트 시작일
		int proj_period = Integer.parseInt(req.getParameter("proj_period")); // 프로젝트
		String proj_regenddate = req.getParameter("proj_regenddate"); // 프로젝트
																		// 등록마감일

		try {
			// 폼에 입력받은 데이터를 프로젝트 테이블에 입력
							
			String sql = "UPDATE PROJECT SET PROJ_TITLE=?, PROJ_INTRO=?, "
					+ "PROJ_REGDATE=sysdate, PROJ_STARTDATE=?, PROJ_PERIOD=?, PROJ_REGENDDATE=?"
					+ "WHERE PROJ_ID=?";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setString(3, proj_startdate); // 프로젝트 시작일
			stmt.setInt(4, proj_period); // 프로젝트 기간
			stmt.setString(5, proj_regenddate); // 프로젝트 마감일
			stmt.setString(6, req_proj_id);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("폼에 입력받은 데이터를 프로젝트 테이블에 입력" + e);
		}

		// 폼에 입력받은 함께할 사람데이터를 proj_app 테이블에 입력
		int mem_id = 0;
		ArrayList<Integer> mem_id_list = new ArrayList<>();
		int proj_id = 0;
		String proj_coworker = req.getParameter("final_result_id");
		String[] proj_coworkers = proj_coworker.split(",");
		try {
			for (int i = 0; i < proj_coworkers.length; i++) {
				String sql_coworker = "SELECT MEM_ID FROM MEMBER WHERE PROF_ID = ?";
				stmt = conn.prepareStatement(sql_coworker);
				stmt.setString(1, proj_coworkers[i]);
				rs = stmt.executeQuery();
				while(rs.next()){
				mem_id_list.add(rs.getInt("mem_id"));
				}
			}
		} catch (Exception e) {
			System.out.println("멤버 테이블에서 멤버 아이디 검색" + e);
		}
		try {
			String sql_proj_id = "SELECT PROJ_ID FROM PROJECT WHERE PROJ_INTRO=? AND PROJ_TITLE=?";
			stmt = conn.prepareStatement(sql_proj_id);
			stmt.setString(1, proj_intro);
			stmt.setString(2, proj_title);
			rs = stmt.executeQuery();
			rs.next();
			proj_id = rs.getInt("PROJ_ID"); // 테그에 사용하는 글번호 - 프로젝트 번호
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}
		try {
			String insert_proj_app_sql = "INSERT INTO PROJ_APP(PROJ_APP_ID, MEM_ID, PROJ_ID, PROJ_APP_REGDATE, PROJ_APP_CONFIRM) VALUES(seq_PROJ_APP_ID.nextval, ?, ?, sysdate, 't')";
			stmt = conn.prepareStatement(insert_proj_app_sql);
			Iterator<Integer> mem_id_iter = mem_id_list.iterator();
			while (mem_id_iter.hasNext()) {
				mem_id = mem_id_iter.next();
				System.out.println(mem_id);
				stmt.setInt(1, mem_id);
				stmt.setInt(2, proj_id);
				rs = stmt.executeQuery();
			}
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}

		// 폼에 입력받은 환경 데이터를 TAG_USE 테이블에 입력을 위한 변수 설정
		String tag_use_type = "PROJ_REG"; // 프로젝트에서 등록된 글임을 표시
		int tag_use_type_id = proj_id; // 테그에 사용하는 글번호 - 프로젝트 번호
		int tag_id = 0; // tag_id를 초기화 이후 select문으로 추가

		// 프로젝트 개발 황경을 TAG, TAG_USE 테이블에 입력
		// 프로젝트 개발 환경에 입력된 내용 호출 및 분리
		StringTokenizer proj_env_st = new StringTokenizer(req.getParameter("proj_env"), ",/^&*");
		while (proj_env_st.hasMoreTokens()) {
			// 폼에 입력받은 환경 데이터를 TAG 테이블에 입력을 위한 쿼리문
			String tag_type = "env";
			String tag_name = proj_env_st.nextToken().trim();
			try {
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				// 자동 입력된 TAG_ID를 TAG 테이블에서 호출
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE='env' AND TAG_NAME='" + tag_name
						+ "'";
				stmt = conn.prepareStatement(select_tag_id_sql);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");

				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			} catch (Exception e) {
				System.out.println("TAG, TAG_USE테이블에 환경 데이터 입력" + e);
			}
		}

		// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
		StringTokenizer proj_language_st = new StringTokenizer(req.getParameter("proj_language"), ",/^&* ");
		while (proj_language_st.hasMoreTokens()) {
			String tag_type = "language";
			String tag_name = proj_language_st.nextToken().trim();
			// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
			try {
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");

				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				rs = stmt.executeQuery();
			} catch (Exception e) {
				System.out.println("TAG, TAG_USE테이블에 언어 데이터 입력" + e);
			}
		}

		// 프로젝트 개발 도구에 입력된 내용 호출 및 분리
		StringTokenizer proj_tool_st = new StringTokenizer(req.getParameter("proj_tool"), ",/^&*");
		while (proj_tool_st.hasMoreTokens()) {
			String tag_type = "tool";
			String tag_name = proj_tool_st.nextToken().trim();
			try {
				// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
				String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("1");
			}
			try {
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");
			} catch (Exception e) {
				System.out.println("2");
			}
			try {
				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("3TAG, TAG_USE테이블에 개발 데이터 입력" + e);
			}
		}

		// 프로젝트 모집 분야 및 인원에 입력된 내용 호출 및 분리
		String[] proj_fields = req.getParameterValues("proj_field"); // 다수의 모집분야
																		// 배열 호출
		String[] proj_numofperson_arr = req.getParameterValues("proj_numofperson"); // 다수의모집인원배열호출,이후사용시정수형변환
		for (int j = 0; j < proj_fields.length; j++) {
			StringTokenizer proj_field_st = new StringTokenizer(proj_fields[j], ",/^&*");
			while (proj_field_st.hasMoreTokens()) {
				String tag_type = "field";
				String tag_name = proj_field_st.nextToken().trim();
				int proj_numofperson = Integer.parseInt(proj_numofperson_arr[j]); // 정수형
																					// 변환
				try {
					// 폼에 입력받은 데이터를 TAG TABLE에 입력을 위한 쿼리문
					String insert_tag_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();

					String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
					stmt = conn.prepareStatement(select_tag_id_sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();
					rs.next();
					tag_id = rs.getInt("tag_id");

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(seq_tag_use_id.nextVal, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.setInt(4, proj_numofperson);
					rs = stmt.executeQuery();
				} catch (Exception e) {
					System.out.println("모집분야 및 인원 테이블에 등록오류 " + e);
				}
			}
		}
	}

}