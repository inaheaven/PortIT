package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
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
	// 프로젝트 작성자 인증

	// 프로젝트 상세 조회

	// 프로젝트 전체 목록
	
	public void read_proj(HttpServletRequest req, HttpServletResponse resp){
		
		String req_proj_id = req.getParameter("proj_id");
		String proj_title;
		String proj_intro;
		String proj_regdate;
		String proj_startdate;
		int proj_period; 
		String proj_regenddate;
		
		int proj_tag_id;	String proj_tag_name;				//TAG_USE 테이블에서 값을 가져올때 사용하기 위한 변수 
		int proj_numofperson = 0;
		ArrayList<Integer> numofperson_list = new ArrayList<>();
		
		String env;
		ArrayList<String> proj_env_list = new ArrayList<>();
		String lang;
		ArrayList<String> proj_language_list = new ArrayList<>();
		String tool;
		ArrayList<String> proj_tool_list = new ArrayList<>();
		String field;
		ArrayList<String> proj_field_list = new ArrayList<>();
		
		ArrayList<Project> update_list = new ArrayList<>();
		ArrayList<Tag> env_list = new ArrayList<>();
		ArrayList<Tag> lang_list = new ArrayList<>();
		ArrayList<Tag> tool_list = new ArrayList<>();
		ArrayList<Tag> field_list = new ArrayList<>();
		ArrayList<Profile> prof_list = new ArrayList<>();
		
		//dto
		Project project = new Project();
		Tag env_tag = new Tag();
		Tag field_tag = new Tag();
		Tag lang_tag = new Tag();
		Tag tool_tag = new Tag();
		Profile profile = new Profile();

		try {
			//project 테이블에서 필요한 값 불러오기
			String proj_sql = "SELECT * FROM PROJECT WHERE PROJ_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(proj_sql);
			stmt.setString(1, req_proj_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			rs.next();
			proj_title = rs.getString("proj_title");
			proj_intro = rs.getString("proj_intro");
			proj_regdate = rs.getString("proj_regdate");
			proj_startdate = rs.getString("proj_startdate");
			proj_period = rs.getInt("proj_period"); 
			proj_regenddate = rs.getString("proj_regenddate");
			
			
			
			//Member 테이블에서 필요한 값 불러오기
			String member_id_sql = "SELECT MEM_ID FROM PROJ_APP WHERE PROJ_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(member_id_sql);
			stmt.setString(1, req_proj_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			rs.next();
			int mem_id = rs.getInt("mem_id");
			String member_name_sql = "SELECT PROF_NAME, PROF_NICK FROM PROFILE WHERE MEM_ID = ?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(member_name_sql);
			stmt.setInt(1, mem_id); // 프로젝트 아이디 입력을 통한 데이터 읽어오기
			rs = stmt.executeQuery();
			rs.next();
			String prof_name = rs.getString("prof_name");
			String prof_nick = rs.getString("prof_nick");
			profile.setProf_name(prof_name);
			profile.setProf_nick(prof_nick);
			prof_list.add(profile);
			
			
			//Tag 테이블에서 필요한 값 불러오기		
			String tag_sql = "SELECT TAG_ID, PROJ_NUMOFPERSON FROM TAG_USE WHERE TAG_USE_TYPE='PROJ_REG' AND TAG_USE_TYPE_ID=?";
			conn = pool.getConnection();
			stmt = conn.prepareStatement(tag_sql);
			stmt.setString(1, req_proj_id);
			rs = stmt.executeQuery();
			ArrayList<Integer> tag_id_list = new ArrayList<>();
			
			while(rs.next()){
				proj_tag_id = rs.getInt("TAG_ID");
				proj_numofperson = rs.getInt("PROJ_NUMOFPERSON");
				if(rs.getInt("PROJ_NUMOFPERSON")!=0){
					numofperson_list.add(proj_numofperson);
				}
				tag_id_list.add(proj_tag_id);
			}
			
			Iterator<Integer> tag = tag_id_list.iterator();
			while(tag.hasNext()){
				proj_tag_id = tag.next();
				String tag_id_sql = "SELECT TAG_TYPE, TAG_NAME FROM TAG WHERE TAG_ID = ?";
				conn = pool.getConnection();
				stmt = conn.prepareStatement(tag_id_sql);
				stmt.setInt(1, proj_tag_id);
				rs = stmt.executeQuery();
				rs.next();
				proj_tag_name = rs.getString("TAG_NAME");
				String tag_type = rs.getString("TAG_TYPE");
				if(tag_type.equals("env")){
					env = proj_tag_name;
					proj_env_list.add(env);
					env_tag.setProj_env_list(proj_env_list);
					env_list.add(env_tag);
				}else if(tag_type.equals("field")){
					field = proj_tag_name;
					proj_field_list.add(field);
					field_tag.setProj_field_list(proj_field_list);
					field_list.add(field_tag);
				}else if(tag_type.equals("language")){
					lang = proj_tag_name;
					proj_language_list.add(lang);
					lang_tag.setProj_lang_list(proj_language_list);
					lang_list.add(lang_tag);
				}else if(tag_type.equals("tool")){
					tool = proj_tag_name;
					proj_tool_list.add(tool);
					tool_tag.setProj_tool_list(proj_tool_list);
					tool_list.add(tool_tag);
				}
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
			view.forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("read_proj오류"+e);
		} finally {
			freeConnection();
		}
		
	}
	
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
					+ "VALUES(seq_mypage_proj_id.nextVal, ?, ?, sysdate, ?, ?, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, proj_title); // 프로젝트 제목
			stmt.setString(2, proj_intro); // 프로젝트 설명
			stmt.setString(3, proj_startdate); // 프로젝트 시작일
			stmt.setInt(4, proj_period); // 프로젝트 기간
			stmt.setString(5, proj_regenddate); // 프로젝트 마감일
			rs = stmt.executeQuery();
			
			
			//프로젝트 함께할 사람에 입력된 내용
			int mem_id=0;
			String proj_coworker = req.getParameter("final_result_id");
			String[] proj_coworkers = proj_coworker.split(",");
			for(int i=0; i<proj_coworkers.length; i++){
				String sql_coworker = "SELECT MEM_ID FROM MEMBER WHERE PROF_ID = ?";
				stmt = conn.prepareStatement(sql_coworker);
				stmt.setString(1, proj_coworkers[i]);
				rs = stmt.executeQuery();
				rs.next();
				mem_id = rs.getInt("mem_id");
				
				String sql_proj_id = "SELECT PROJ_ID FROM PROJECT WHERE PROJ_INTRO='" + proj_intro + "'";
				stmt = conn.prepareStatement(sql_proj_id);
				rs = stmt.executeQuery();
				rs.next();
				int proj_id = rs.getInt("PROJ_ID");	//테그에 사용하는 글번호 - 프로젝트 번호
				
				
				sql = "INSERT INTO PROJ_APP(PROJ_APP_ID, MEM_ID, PROJ_ID, PROJ_APP_REGDATE, PROJ_APP_CONFIRM) VALUES(seq_PROJ_APP_ID.nextval, ?, ?, sysdate, 't')";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, mem_id);
				stmt.setInt(2, proj_id);
				rs = stmt.executeQuery();
			}
			
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

				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				// 자동 입력된 TAG_ID를 TAG 테이블에서 호출
				String sql_tag_id = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE='env' AND TAG_NAME='" + tag_name + "'";
				stmt = conn.prepareStatement(sql_tag_id);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");			

				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
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
				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();

				String sql_tag_id = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(sql_tag_id);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");	
				
				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
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
				sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_mypage_proj_tag_id.nextVal, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				

				String sql_tag_id = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
				stmt = conn.prepareStatement(sql_tag_id);
				stmt.setString(1, tag_type);
				stmt.setString(2, tag_name);
				rs = stmt.executeQuery();
				rs.next();
				tag_id = rs.getInt("tag_id");	
				
				sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID) VALUES(seq_mypage_proj_tag_use_id.nextVal, ?, ?, ?)";
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
					sql = "INSERT INTO TAG(TAG_ID, TAG_TYPE, TAG_NAME) VALUES(seq_mypage_proj_tag_id.nextVal, ?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();
					

					String sql_tag_id = "SELECT TAG_ID FROM TAG WHERE TAG_TYPE=? AND TAG_NAME=?";
					stmt = conn.prepareStatement(sql_tag_id);
					stmt.setString(1, tag_type);
					stmt.setString(2, tag_name);
					rs = stmt.executeQuery();
					rs.next();
					tag_id = rs.getInt("tag_id");	
					
					sql = "INSERT INTO TAG_USE(TAG_USE_ID, TAG_USE_TYPE, TAG_USE_TYPE_ID, TAG_ID, PROJ_NUMOFPERSON) VALUES(seq_mypage_proj_tag_use_id.nextVal, ?, ?, ?, ?)";
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




/**
	 * 회원을 이름/닉네임을 통해서 검사
	 * @param req
	 * @param resp
	 */
	public ArrayList<Profile> list = new ArrayList<>();
	public void findMemberbyName(HttpServletRequest req, HttpServletResponse resp){
		
		String coworker = req.getParameter("name");
		String sql="SELECT PROF_NAME, PROF_NICK, PROF_ID FROM PROFILE WHERE PROF_NAME=?";
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, coworker);
			rs = stmt.executeQuery();
			
			while(rs.next()){
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

}