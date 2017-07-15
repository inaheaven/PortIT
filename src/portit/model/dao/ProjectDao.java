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
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;
import portit.model.dto.Project;
import portit.model.dto.ProjectApp_mem;
import portit.model.dto.Tag;

public class ProjectDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;

	private TagDao tagDao;
	
	public ProjectDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			TagDao tagDao = new TagDao(); 
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

		String req_proj_id = req.getParameter("param"); // 프로젝트 아이디 번호
		String proj_title = ""; // 프로젝트 테이블에서 사용할 변수
		String proj_intro = "";
		int proj_period = 0;

		int proj_tag_id; // 테그 테이블에서 사용할 변수
		String proj_tag_name; // 테그명
		String lang = "";
		ArrayList<String> proj_language_list = new ArrayList<>();
		String tool = "";
		ArrayList<String> proj_tool_list = new ArrayList<>();
		String field = "";
		ArrayList<String> proj_field_list = new ArrayList<>();
		int proj_numofperson = 0;
		ArrayList<Integer> numofperson_list = new ArrayList<>();

		String prof_name = "";
		String prof_nick = "";
		ArrayList<String> prof_name_list = new ArrayList<>();
		ArrayList<String> prof_nick_list = new ArrayList<>();

		int coworker = 0;
		ArrayList<Integer> coworker_list = new ArrayList<>();

		int prof_id = 0;

		// dto
		Project project = new Project();
		Tag field_tag = new Tag();
		Tag lang_tag = new Tag();
		Tag tool_tag = new Tag();
		Profile profile = new Profile();

		// dto 입력후 request를 통한 전달을 위한 ArrayList
		ArrayList<Project> update_list = new ArrayList<>();
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
				project.setProj_title(proj_title);

				proj_intro = rs.getString("proj_intro");
				project.setProj_intro(proj_intro);

				Date proj_regdate_format = rs.getDate("proj_regdate");
				project.setProj_regdate(proj_regdate_format);

				Date proj_startdate_format = rs.getDate("proj_startdate");
				project.setProj_startdate(proj_startdate_format);

				proj_period = rs.getInt("proj_period");
				project.setProj_period(proj_period);

				Date proj_regenddate_format = rs.getDate("proj_regenddate");
				project.setProj_regenddate(proj_regenddate_format);
			}
		} catch (Exception e) {
			System.out.println("project 테이블 쿼리 오류" + e);
		}

		// 검색시 활용하기 위한 회원 아이디 번호
		ArrayList<Integer> mem_id_list = new ArrayList<>();
		try {
			// count를 이용하여 프로젝트에 참여하는 인원수를 구해서 이후 해당 값만큼 반복하여 멤버리스트에추가
			String sql_coworker = "SELECT MEM_ID FROM PROJ_APP WHERE PROJ_ID = ?";
			stmt = conn.prepareStatement(sql_coworker);
			stmt.setString(1, req_proj_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mem_id_list.add(rs.getInt("mem_id"));
			}
		} catch (Exception e) {
			System.out.println("멤버 테이블에서 멤버 아이디 검색2" + e);
		}

		try {
			Iterator<Integer> iter = mem_id_list.iterator();
			while (iter.hasNext()) {
				String member_name_sql = "SELECT PROF_ID, PROF_NAME, PROF_NICK FROM PROFILE WHERE MEM_ID = ?";
				conn = pool.getConnection();
				stmt = conn.prepareStatement(member_name_sql);
				stmt.setInt(1, iter.next()); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
				rs = stmt.executeQuery();
				if (rs.next()) {
					prof_id = rs.getInt("prof_id");
					prof_name = rs.getString("prof_name");
					prof_nick = rs.getString("prof_nick");
					coworker_list.add(prof_id);
					prof_name_list.add(prof_name);
					prof_nick_list.add(prof_nick);
					profile.setProf_id_list(coworker_list);
					profile.setProf_name_list(prof_name_list);
					profile.setProf_nick_list(prof_nick_list);
				}
			}
			prof_list.add(profile);
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
				if (rs.next()) {
					proj_tag_name = rs.getString("TAG_NAME");
					String tag_type = rs.getString("TAG_TYPE");
					if (tag_type.equals("field")) {
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
			}
		} catch (Exception e) {
			System.out.println("tag 테이블에서 쿼리 오류" + e);
		} finally {
			freeConnection();
		}

		project.setProj_numofperson(numofperson_list);
		project.setProj_id(Integer.parseInt(req_proj_id));
		update_list.add(project);

		req.setAttribute("list", update_list);
		req.setAttribute("language_list", lang_list);
		req.setAttribute("tool_list", tool_list);
		req.setAttribute("field_list", field_list);
		req.setAttribute("prof_list", prof_list);

	}

	/**
	 * 프로젝트 등록
	 * 
	 * @param req
	 * @param resp
	 */
	public void reg_pro(HttpServletRequest req, HttpServletResponse resp) {
		// 내 아이디
		HttpSession session = req.getSession();
		String login_id = String.valueOf((int) session.getAttribute("loginId"));
		String proj_title = req.getParameter("proj_title"); // 프로젝트 제목
		String proj_intro = req.getParameter("proj_intro"); // 프로젝트 내용
		String proj_startdate = req.getParameter("proj_startdate"); // 프로젝트 시작일
		int proj_period = Integer.parseInt(req.getParameter("proj_period")); // 프로젝트
		String proj_regenddate = req.getParameter("proj_regenddate"); // 프로젝트
																		// 등록마감일
		int proj_to = 0;
		String proj_tos[] = req.getParameterValues("proj_numofperson");
		for (int i = 0; i < proj_tos.length; i++) {
			proj_to += Integer.parseInt(proj_tos[i]);
		}
		try {
			// 폼에 입력받은 데이터를 프로젝트 테이블에 입력
			String sql = "INSERT INTO PROJECT "
					+ "(PROJ_ID, PROJ_TITLE, PROJ_INTRO, PROJ_TO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE) "
					+ "VALUES(seq_proj_id.nextVal, ?, ?, ?,sysdate, ?, ?, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setInt(3, proj_to); // 프로젝트 시작일
			stmt.setString(4, proj_startdate); // 프로젝트 시작일
			stmt.setInt(5, proj_period); // 프로젝트 기간
			stmt.setString(6, proj_regenddate); // 프로젝트 마감일
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
				String sql_coworker = "SELECT MEM_ID FROM PROFILE WHERE PROF_ID = ?";
				stmt = conn.prepareStatement(sql_coworker);
				stmt.setString(1, proj_coworkers[i]);
				rs = stmt.executeQuery();
				while (rs.next()) {
					mem_id_list.add(rs.getInt("mem_id"));
				}
			}
		} catch (Exception e) {
			System.out.println("멤버 테이블에서 멤버 아이디 검색3" + e);
		}
		try {
			String sql_proj_id = "SELECT PROJ_ID FROM PROJECT WHERE PROJ_INTRO=? AND PROJ_TITLE=?";
			stmt = conn.prepareStatement(sql_proj_id);
			stmt.setString(1, proj_intro);
			stmt.setString(2, proj_title);
			rs = stmt.executeQuery();
			if (rs.next()) {
				proj_id = rs.getInt("PROJ_ID"); // 테그에 사용하는 글번호 - 프로젝트 번호
			}
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}
		try {
			String join = "y";
			String insert_proj_app_sql = "INSERT INTO PROJ_APP(PROJ_APP_ID, MEM_ID, PROJ_ID, PROJ_APP_REGDATE, PROJ_APP_CONFIRM) VALUES(seq_PROJ_APP_ID.nextval, ?, ?, sysdate, ?)";
			stmt = conn.prepareStatement(insert_proj_app_sql);
			Iterator<Integer> mem_id_iter = mem_id_list.iterator();
			while (mem_id_iter.hasNext()) {
				mem_id = mem_id_iter.next();
				stmt.setInt(1, mem_id);
				stmt.setInt(2, proj_id);
				stmt.setString(3, join);
				rs = stmt.executeQuery();
			}
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}

		// 개발 언어
		// 폼에 입력받은 환경 데이터를 TAG_USE 테이블에 입력을 위한 변수 설정
		String tag_use_type = "PROJ_REG"; // 프로젝트에서 등록된 글임을 표시
		int tag_use_type_id = proj_id; // 테그에 사용하는 글번호 - 프로젝트 번호
		int tag_id = 0; // tag_id를 초기화 이후 select문으로 추가

		String tag_name = "";
		String[] tag_lang = req.getParameterValues("tag_lang");
		for (int i = 0; i < tag_lang.length; i++) {
			// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
			String tag_lang_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =? AND TAG_TYPE='language'";
			try {
				String tag_type = "language";
				tag_name = tag_lang[i];
				stmt = conn.prepareStatement(tag_lang_search_sql);
				stmt.setString(1, tag_lang[i]);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_name = rs.getString("TAG_NAME");
					System.out.println("등록이 이미 되어있으니 TAG등록 패쓰");
				} else {
					if (!tag_lang[i].equals("")) {
						String tag_lang_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_lang_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_id = rs.getInt("tag_id");

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.executeQuery();
				}
			} catch (SQLException e) {
				System.out.println("개발 언어 입력 문제" + e);
			}
		}

		// 개발도구
		tag_name = "";
		String[] tag_tool = req.getParameterValues("tag_tool");
		for (int i = 0; i < tag_tool.length; i++) {
			// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
			String tag_tool_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =? AND TAG_TYPE='tool'";
			try {
				stmt = conn.prepareStatement(tag_tool_search_sql);
				stmt.setString(1, tag_tool[i]);
				rs = stmt.executeQuery();
				String tag_type = "tool";
				tag_name = tag_tool[i];
				if (rs.next()) { // 등록된 테그는 패쓰하고, 등록되지 않은 태그만 검색결과로 테그 테이블에 등록
					tag_name = rs.getString("TAG_NAME");
				} else {
					if (!tag_tool[i].equals("")) {
						String tag_tool_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_tool_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if (rs.next()) {

					tag_id = rs.getInt("tag_id");
					System.out.println(tag_id);
					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.executeQuery();
				}
			} catch (SQLException e) {
				System.out.println("개발 언어 입력 문제" + e);
			}
		}

		// 프로젝트 모집 분야 및 인원에 입력된 내용 호출 및 분리
		String[] tag_fields = req.getParameterValues("proj_field"); // 다수의 모집분야
		String[] tag_numofperson_arr = req.getParameterValues("proj_numofperson"); // 다수의모집인원배열호출,이후사용시정수형변환
		for (int i = 0; i < tag_fields.length; i++) {
			String tag_type = "field";
			tag_name = tag_fields[i].trim();
			int proj_numofperson = Integer.parseInt(tag_numofperson_arr[i]); // 정수형
			try {
				String tag_field_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =? AND TAG_TYPE='field'";
				stmt = conn.prepareStatement(tag_field_search_sql);
				stmt.setString(1, tag_fields[i]);
				rs = stmt.executeQuery();
				tag_name = tag_fields[i];
				if (rs.next()) { // 등록된 테그는 패쓰하고, 등록되지 않은 태그만 검색결과로 테그 테이블에 등록
					tag_name = rs.getString("TAG_NAME");
				} else {
					if (!tag_fields[i].equals("")) {
						String tag_field_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_field_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if(rs.next()){
					
				tag_id = rs.getInt("tag_id");

				String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(seq_tag_use_id.nextVal, ?, ?, ?, ?)";
				stmt = conn.prepareStatement(insert_tag_use_sql);
				stmt.setString(1, tag_use_type);
				stmt.setInt(2, tag_use_type_id);
				stmt.setInt(3, tag_id);
				stmt.setInt(4, proj_numofperson);
				rs = stmt.executeQuery();
				}
			} catch (Exception e) {
				System.out.println("모집분야 및 인원 테이블에 등록오류 " + e);
			}

		}

		try {
			String insert_my_proj = "INSERT INTO MEM_PROJ(MEM_PROJ_ID, PROJ_ID, MEM_ID) VALUES(seq_mem_proj_id.nextVal, ?, ?)";
			stmt = conn.prepareStatement(insert_my_proj);
			stmt.setInt(1, proj_id);
			stmt.setString(2, login_id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("내가 등록한 프로젝트 테이블에 등록 오류 " + e);
		}

		System.out.println("모든게 등록되었다.");

	}

	/**
	 * 회원을 이름/닉네임을 통해서 검사
	 * 
	 * @param req
	 * @param resp
	 */
	// 함께할 사람등록을 위한 회원이름검색 - 회원이름 닉네임을 통해서 검사시 필요한 전역변수지정 리스트
	public ArrayList<Profile> list = new ArrayList<>();

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
			System.out.println("최종" + e);
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
		String req_proj_id = req.getParameter("proj_id");
		String proj_title = req.getParameter("proj_title"); // 프로젝트 제목
		String proj_intro = req.getParameter("proj_intro"); // 프로젝트 내용
		String proj_startdate = req.getParameter("proj_startdate"); // 프로젝트 시작일
		int proj_period = Integer.parseInt(req.getParameter("proj_period")); // 프로젝트
		String proj_regenddate = req.getParameter("proj_regenddate"); // 프로젝트
																		// 등록마감일

		int proj_to = 0;
		String proj_tos[] = req.getParameterValues("proj_numofperson");
		for (int i = 0; i < proj_tos.length; i++) {
			proj_to += Integer.parseInt(proj_tos[i]);
		}
		try {
			// 폼에 입력받은 데이터를 프로젝트 테이블에 입력
			String sql = "UPDATE PROJECT SET PROJ_TITLE=?, PROJ_INTRO=?, PROJ_TO=?,"
					+ "PROJ_REGDATE=sysdate, PROJ_STARTDATE=?, PROJ_PERIOD=?, PROJ_REGENDDATE=?" + "WHERE PROJ_ID=?";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setInt(3, proj_to);
			stmt.setString(4, proj_startdate); // 프로젝트 시작일
			stmt.setInt(5, proj_period); // 프로젝트 기간
			stmt.setString(6, proj_regenddate); // 프로젝트 마감일
			stmt.setString(7, req_proj_id);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("폼에 입력받은 데이터를 프로젝트 테이블에 입력" + e);
		}

		// 폼에 입력받은 함께할 사람데이터를 proj_app 테이블에 입력
		int mem_id = 0;
		int proj_id = 0;
		ArrayList<Integer> mem_id_list = new ArrayList<>();
		String[] proj_coworkers = req_proj_id.split(",");
		try {
			for (int i = 0; i < proj_coworkers.length; i++) {
				String sql_coworker = "SELECT MEM_ID FROM PROFILE WHERE PROF_ID = ?";
				stmt = conn.prepareStatement(sql_coworker);
				stmt.setString(1, proj_coworkers[i]);
				rs = stmt.executeQuery();
				while (rs.next()) {
					mem_id_list.add(rs.getInt("mem_id"));
				}
			}
		} catch (Exception e) {
			System.out.println("멤버 테이블에서 멤버 아이디 검색1" + e);
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
			String insert_proj_app_sql = "UPDATE PROJ_APP SET MEM_ID=4 WHERE PROJ_ID=? AND MEM_ID=?";
			stmt = conn.prepareStatement(insert_proj_app_sql);
			Iterator<Integer> mem_id_iter = mem_id_list.iterator();
			while (mem_id_iter.hasNext()) {
				mem_id = mem_id_iter.next();
				stmt.setInt(1, mem_id);
				stmt.setInt(2, proj_id);
				rs = stmt.executeQuery();
				System.out.println("이거 왜 안돼!");
			}
		} catch (Exception e) {
			System.out.println("검색 내용PROJECT_APP에 입력 쿼리" + e);
		}

		// 개발 언어
		// 폼에 입력받은 환경 데이터를 TAG_USE 테이블에 입력을 위한 변수 설정
		String tag_use_type = "PROJ_REG"; // 프로젝트에서 등록된 글임을 표시
		int tag_use_type_id = proj_id; // 테그에 사용하는 글번호 - 프로젝트 번호
		int tag_id = 0; // tag_id를 초기화 이후 select문으로 추가

		String delete_tag_use_sql = "DELETE FROM TAG_USE WHERE TAG_USE_TYPE_ID=?";
		try {
			stmt = conn.prepareStatement(delete_tag_use_sql);
			stmt.setInt(1, tag_use_type_id);
			stmt.executeQuery();
		} catch (SQLException e1) {
			System.out.println("기존의 TAG_USE 모두 제거");
		}

		String tag_name = "";
		String[] tag_lang = req.getParameterValues("tag_lang");
		for (int i = 0; i < tag_lang.length; i++) {
			// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
			String tag_lang_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =?";
			try {
				String tag_type = "language";
				tag_name = tag_lang[i];
				stmt = conn.prepareStatement(tag_lang_search_sql);
				stmt.setString(1, tag_lang[i]);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_name = rs.getString("TAG_NAME");
					System.out.println("등록이 이미 되어있으니 TAG등록 패쓰");
				} else {
					if (!tag_lang[i].equals("")) {
						String tag_lang_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_lang_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_id = rs.getInt("tag_id");
					System.out.println(tag_id + "언어 입력전");

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.executeQuery();
				}
			} catch (SQLException e) {
				System.out.println("개발 언어 입력 문제" + e);
			}
		}

		// 개발도구
		tag_name = "";
		String[] tag_tool = req.getParameterValues("tag_tool");
		for (int i = 0; i < tag_tool.length; i++) {
			// 프로젝트 개발 언어에 입력된 내용 호출 및 분리
			String tag_tool_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =?";
			try {
				stmt = conn.prepareStatement(tag_tool_search_sql);
				stmt.setString(1, tag_tool[i]);
				rs = stmt.executeQuery();
				String tag_type = "tool";
				tag_name = tag_tool[i];
				if (rs.next()) { // 등록된 테그는 패쓰하고, 등록되지 않은 태그만 검색결과로 테그 테이블에 등록
					tag_name = rs.getString("TAG_NAME");
				} else {
					if (!tag_tool[i].equals("")) {
						String tag_tool_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_tool_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_id = rs.getInt("tag_id");
					System.out.println(tag_id);

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.executeQuery();
				}

			} catch (SQLException e) {
				System.out.println("개발 언어 입력 문제" + e);
			}
		}

		// 프로젝트 모집 분야 및 인원에 입력된 내용 호출 및 분리
		String[] tag_fields = req.getParameterValues("proj_field"); // 다수의 모집분야
		String[] tag_numofperson_arr = req.getParameterValues("proj_numofperson"); // 다수의모집인원배열호출,이후사용시정수형변환
		for (int i = 0; i < tag_fields.length; i++) {
			String tag_type = "field";
			tag_name = tag_fields[i].trim();
			int proj_numofperson = Integer.parseInt(tag_numofperson_arr[i]); // 정수형
			try {
				String tag_field_search_sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME =?";
				stmt = conn.prepareStatement(tag_field_search_sql);
				stmt.setString(1, tag_fields[i]);
				rs = stmt.executeQuery();
				tag_name = tag_fields[i];
				if (rs.next()) { // 등록된 테그는 패쓰하고, 등록되지 않은 태그만 검색결과로 테그 테이블에 등록
					tag_name = rs.getString("TAG_NAME");
				} else {
					if (!tag_fields[i].equals("")) {
						String tag_field_reg_sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_tag_id.nextVal, ?, ?)";
						stmt = conn.prepareStatement(tag_field_reg_sql);
						stmt.setString(1, tag_type);
						stmt.setString(2, tag_name);
						stmt.executeQuery();
					}
				}
				String select_tag_id_sql = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(select_tag_id_sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				if (rs.next()) {
					tag_id = rs.getInt("tag_id");

					String insert_tag_use_sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(seq_tag_use_id.nextVal, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(insert_tag_use_sql);
					stmt.setString(1, tag_use_type);
					stmt.setInt(2, tag_use_type_id);
					stmt.setInt(3, tag_id);
					stmt.setInt(4, proj_numofperson);
					rs = stmt.executeQuery();
				}
			} catch (Exception e) {
				System.out.println("모집분야 및 인원 테이블에 등록오류 " + e);
			}

		}

	}

	/**
	 * 프로젝트 페이지 호출
	 * 
	 * @param req
	 * @param resp
	 */

	// DAo 준비물....
	List rsList;
	String sql;
	Project Prodto; // 프로젝트 DTO
	ProjectApp_mem proj_app_mem;

	// 프로젝트 작성자 인증

	// 프로젝트 상세 조회

	// 1.내가 등록한 PJ명단.
	public List RegPJlistName(String login_id) {
		// 인스턴스 생성.
		rsList = new ArrayList();
		sql = "select proj_id from mem_proj where mem_id='" + login_id + "'";
		// 필요한 정보: proj_title, proj_id, PROJ_REGENDDATE, mem_id, prof_name,
		// proj_app_confirm
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				rsList.add(rs.getString("proj_id"));
			}
		} catch (Exception e) {
			System.out.println("DAO.applyProjectList()에서 에러!!" + e);
		} finally {
			freeConnection();
		}
		return rsList;
	}

	// 2.프로젝트 정보를 반환...
	public Project regProjectList(String pj_Id) {
		// 프로젝트 id, 프로젝트 이름, 마감일
		// 인스턴스 생성.
		sql = "select proj_title, PROJ_REGENDDATE-sysdate,proj_id    " + "from project " + "where proj_id='" + pj_Id
				+ "' ";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Prodto = new Project();
				Prodto.setProj_title(rs.getString("proj_title")); // 제목
				Prodto.setProj_id(rs.getInt("proj_id")); // id
				Prodto.setD_day((rs.getInt("PROJ_REGENDDATE-sysdate"))); // 마감일

				System.out.println("DAO.ck   " + Prodto.getD_day());
			}
		} catch (Exception e) {
			System.out.println("DAO.regProjectList()에서 에러!!");
			System.out.println(e);
		} finally {
			freeConnection();
		}
		return Prodto;
	}

	// 3.PJ지원자 리스트.
	public List ApplyMemList(String proj_id) {

		// 인출정보 : 이름,닉네임, mem_id, 참여여부
		rsList = new ArrayList();
		sql = "select proj_app.mem_id, profile.prof_name, profile.prof_nick, proj_app.proj_app_confirm "
				+ "from proj_app inner join profile on proj_app.mem_id=profile.mem_id " + "where proj_id='" + proj_id
				+ "' " + "order by PROJ_APP_REGDATE desc";

		// System.out.println("dao "+sql);
		// 쿼리 이상무.

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				proj_app_mem = new ProjectApp_mem();
				proj_app_mem.setMem_id(rs.getString("mem_id")); // mem_id
				proj_app_mem.setName(rs.getString("prof_name")); // 이름
				proj_app_mem.setNick(rs.getString("prof_nick")); // 닉네임
				proj_app_mem.setApp_confirm(rs.getString("proj_app_confirm")); // 참여여부
				rsList.add(proj_app_mem);
			}

		} catch (Exception e) {
			System.out.println("DAO.ApplyMemList()에서 에러!!");
			System.out.println(e);
		} finally {
			freeConnection();
		}
		return rsList;
	}

	// 업데이트문.
	public void pjJoin(String mem_id) {
		sql = "update proj_app set proj_app_confirm='y' " + "where mem_id='" + mem_id + "'";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}

		catch (Exception err) {
			System.out.println("[DAO]: pjJoin()에서 오류");
			err.printStackTrace();
		}

		finally {
			pool.freeConnection(conn, stmt);
		}
	}

	// 팀원 커트
	public void mem_delete(String pj_id, String mem_id) {

		String sql = "delete from PROJ_APP where MEM_ID='" + mem_id + "'" + "and PROJ_ID='" + pj_id + "'";

		System.out.println("dao  " + sql);

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("mem_delete()에서 오류");
			err.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}

	}

	// 1.내가 PJ지원한 리스트.
	public List applyProjectList(String login_id) {

		// 인스턴스 생성.
		rsList = new ArrayList();
		sql = "select  DISTINCT project.proj_title, project.proj_id, project.proj_regdate,  project.proj_regenddate-sysdate, project.proj_startDate, proj_app.proj_app_confirm "
				+ "from project inner join proj_app on project.proj_id=proj_app.proj_id  "
				+ "where project.proj_id in (select proj_id from proj_app where mem_id='" + login_id + "')";

		// 필요한 정보: proj_title, proj_id, PROJ_REGENDDATE, mem_id, prof_name,
		// proj_app_confirm

		System.out.println("dao  " + sql);

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Prodto = new Project();
				Prodto.setProj_id(rs.getInt("proj_id"));
				Prodto.setProj_title(rs.getString("proj_title"));

				// Prodto.setProj_regenddate(((rs.getString("proj_regenddate"))));
				Prodto.setD_day((((rs.getInt("project.proj_regenddate-sysdate")))));
				String proj_regdate = rs.getString("proj_regdate");
				Date proj_regdate_format = new SimpleDateFormat("yyyy-mm-dd").parse(proj_regdate);
				Prodto.setProj_regdate(proj_regdate_format);
				String proj_startdate = rs.getString("proj_startDate");
				Date proj_regstart_format = new SimpleDateFormat("yyyy-mm-dd").parse(proj_startdate);
				Prodto.setProj_startdate(proj_regstart_format);
				Prodto.setProj_app_confirm(rs.getString("proj_app_confirm"));

				rsList.add(Prodto);
			}

		} catch (Exception e) {
			System.out.println("DAO.applyProjectList()에서 에러!!");
			System.out.println(e);
		} finally {
			freeConnection();
		}
		return rsList;
	}

	
	public Project getProjectById(int articleId) {
		Project project = new Project();
		getConnection();
		try {
			// project 테이블에서 필요한 값 불러오기
			String sql = "SELECT PROJ_TITLE, PROJ_INTRO, PROJ_REGDATE, PROJ_STARTDATE, PROJ_PERIOD, PROJ_REGENDDATE FROM PROJECT WHERE PROJ_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, articleId); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			while (rs.next()) {
				project.setProj_title(rs.getString("proj_title"));
				project.setProj_intro(rs.getString("proj_intro"));
				project.setProj_regdate(rs.getDate("proj_regdate"));
				project.setProj_startdate(rs.getDate("proj_startdate"));
				project.setProj_period(rs.getInt("proj_period"));
				project.setProj_regenddate(rs.getDate("proj_regenddate"));
				
				List<Tag> proj_tags_language = tagDao.selectList(conn, "language", "project", articleId);
				ArrayList<String> proj_language = new ArrayList<String>();
				for (Tag tag : proj_tags_language) {
					proj_language.add(tag.getTag_name());
				}
				project.setProj_language(proj_language);
				List<Tag> proj_tags_tool = tagDao.selectList(conn, "tool", "project", articleId);
				ArrayList<String> proj_tool = new ArrayList<String>();
				for (Tag tag : proj_tags_tool) {
					proj_tool.add(tag.getTag_name());
				}
				project.setProj_tool(proj_tool);
				List<Tag> proj_tags_field = tagDao.selectList(conn, "field", "project", articleId);
				ArrayList<String> proj_field = new ArrayList<String>();
				for (Tag tag : proj_tags_field) {
					proj_field.add(tag.getTag_name());
				}
				project.setProj_field(proj_field);

				ArrayList<Integer> proj_numofperson = new ArrayList<Integer>();
				sql = "SELECT * FROM proj_app WHERE proj_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, articleId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					proj_numofperson.add(new Integer(rs.getInt("mem_id")));
				}
				project.setProj_numofperson(proj_numofperson);
				project.setProj_to(proj_numofperson.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}
}