package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs, rs2;
	private DBConnectionMgr pool;
	private String sql = null;
	
	private MediaDao mediaDao;
	private PortfolioDao portfolioDao;
	private TagDao tagDao;

	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			//conn = pool.getConnection();
		} catch (Exception e) {
			System.out.println("DB 접속 오류 :");
			e.printStackTrace();
		}
	}
	
	/**
	 * DB 연결
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null) {
				System.out.println("DB 접속 : "+this.getClass().getName());
			}
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
				System.out.println("DB 접속 해제 : "+this.getClass().getName());
			}
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 :");
			e.printStackTrace();
		}
	}
	

	/**
	 * 프로필 등록(프로필 테이블 + 태크 테이블에 있는 정보 등록)
	 */
	public Profile addprofile(Profile dto, int mem_id) {
		try {			
			sql = "insert into profile(prof_id, mem_id, prof_img, prof_background, prof_name, prof_nick, prof_intro, prof_website, prof_facebook, prof_github, prof_regdate, prof_follower) "
					+ "values(seq_prof_id.nextVal, ? , ? , ? , ? , ? , ? , ?, ?, ? , sysdate, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setString(2, dto.getProf_img());
			stmt.setString(3, dto.getProf_background());
			stmt.setString(4, dto.getProf_name());
			stmt.setString(5, dto.getProf_nick());
			stmt.setString(6, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());
			stmt.setString(8, dto.getProf_facebook());
			stmt.setString(9, dto.getProf_github());
			stmt.setInt(10, dto.getProf_follower());

			stmt.executeUpdate();
			
			List tag_lang = dto.getTag_lang();
			List tag_tool = dto.getTag_tool();
			List tag_field = dto.getTag_field();
			List tag_skill = dto.getTag_skill();
			List prof_skill_level = dto.getProf_skill_level();
			//////////////////////////////////////////////////////////
			System.out.println("tool1 : " + tag_tool.get(0).toString());
			System.out.println("1st : " + tag_field.get(0).toString());
			System.out.println("2nd : " + tag_field.get(1).toString());
			//System.out.println("3nd : " + tag_lang.get(2).toString());
			//////////////////////////////////////////////////////////
			List tag_lang2 = new ArrayList();
			List tag_tool2 = new ArrayList();
			List tag_field2 = new ArrayList();
			List tag_skill2 = new ArrayList();
			
			
			//태그 테이블 추가//////////////////////////////////////////////
			//언어
			if (tag_lang != null) {
				boolean check = true;
				
				for (int i = 0; i < tag_lang.size(); i++) {					
					check = tagNameCheck(tag_lang.get(i).toString());
					
					if (!check) {
						tag_lang2.add(tag_lang.get(i).toString());
					}
				}
				System.out.println("list길이 :" +tag_lang2.size());
				
				for (int i = 0; i < tag_lang2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "language");
					stmt.setString(2, tag_lang2.get(i).toString());
					stmt.executeUpdate();	
					
				}
			}			
			//태그 툴 입력
			if (tag_tool != null) {
				boolean check;

				for (int i = 0; i < tag_tool.size(); i++) {
					check = tagNameCheck(tag_tool.get(i).toString());
					
					if (!check) {
						tag_tool2.add(tag_tool.get(i).toString());
					}
				}
				
				for (int i = 0; i < tag_tool2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "tool");
					stmt.setString(2, tag_tool2.get(i).toString());
					stmt.executeUpdate();

				}
			}
			
			
			//분야(필드)
			if (tag_field != null) {
				boolean check;

				for (int i = 0; i < tag_field.size(); i++) {
					check = tagNameCheck(tag_field.get(i).toString());

					if (!check) {
						tag_field2.add(tag_field.get(i).toString());
					}
				}

				for (int i = 0; i < tag_field2.size(); i++) {
						sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "field");
						stmt.setString(2, tag_field2.get(i).toString());
						stmt.executeUpdate();
					}
				}
			
			// 스킬
			if (tag_skill != null) {
				boolean check;
				
				for (int i = 0; i < tag_skill.size(); i++) {
					check = tagNameCheck(tag_skill.get(i).toString());
					
					if (!check) {
						tag_skill2.add(tag_skill.get(i).toString());
					}
				}
				
				for (int i = 0; i < tag_skill2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "skill_level");
					stmt.setString(2, tag_skill2.get(i).toString());
					stmt.executeUpdate();
				}
			}
			

			
			//프로필 아이디를 가져옴
			int req_prof_id = getProf_id(mem_id);
			System.out.println("프로필 아이디 : " + req_prof_id);	
			
			List tag_lang3 = new ArrayList();
			List tag_tool3 = new ArrayList();
			List tag_field3 = new ArrayList();
			List tag_skill3 = new ArrayList();
			List prof_skill_level3 = new ArrayList();
			
			
			boolean check = true;
			//입력한 태그의 갯수가 몇 개 인지 갯수 추출
			for (int i = 0; i < tag_lang.size(); i++) {					
				check = tagNameCheck(tag_lang.get(i).toString());
				
				if (!check) {
					tag_lang3.add(tag_lang.get(i).toString());
				}
			}
			
			for(int i=0; i<tag_lang.size(); i++){
				if("".equals(tag_lang.get(i).toString()) || " ".equals(tag_lang.get(i).toString()) || null == tag_lang.get(i).toString()){
					continue;
				}
				else{				
					tag_lang3.add(tagNameToId(tag_lang.get(i).toString()));
				}
			}
			System.out.println("태그랭 길이 :" +tag_lang3.size());
			
			
			//태그유즈에서 language등록
			if (tag_lang != null) {
				sql = "INSERT INTO tag_use("
						+ "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
						+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
				for (int i = 0; i < tag_lang3.size(); i++) {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_lang3.get(i));
					stmt.executeUpdate();
				}
			}
			
			//태그 유즈에 tool을 넣기 위한 리스트
			for(int i=0; i<tag_tool.size(); i++){
				if("".equals(tag_tool.get(i).toString()) || " ".equals(tag_tool.get(i).toString()) || null == tag_tool.get(i).toString()){
					continue;
				}
				else{
					tag_tool3.add(tagNameToId(tag_tool.get(i).toString()));
				}
			}			
		
			//툴 유즈
			if (tag_tool != null) {
				for (int i = 0; i < tag_tool3.size(); i++) {
					sql = "INSERT INTO tag_use("
							+ "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_tool3.get(i));
					stmt.executeUpdate();
				}
			}
			
			//태그 유즈에 field을 넣기 위한 리스트
			for(int i=0; i<tag_field.size(); i++){
				if("".equals(tag_field.get(i).toString()) || " ".equals(tag_field.get(i).toString()) || null == tag_field.get(i).toString()){
					continue;
				}
				else{
					tag_field3.add(tagNameToId(tag_field.get(i).toString()));
				}
			}
			
			//분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_field3.size(); i++) {
					sql = "INSERT INTO tag_use("
							+ "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_field3.get(i));
					stmt.executeUpdate();
				}
			}
			
			//태그 유즈에 스킬 점수를 넣기 위한 리스트
			for(int i=0; i<tag_skill.size(); i++){
				if("".equals(tag_skill.get(i).toString()) || " ".equals(tag_skill.get(i).toString()) || null == tag_skill.get(i).toString()){
					continue;
				}
				else{
					tag_skill3.add(tagNameToId(tag_skill.get(i).toString()));
				}
			}
			
			for(int i=0; i<prof_skill_level.size(); i++){
				if("".equals(prof_skill_level.get(i).toString()) || " ".equals(prof_skill_level.get(i).toString()) || null == prof_skill_level.get(i).toString()){
					continue;
				}
				else{
					prof_skill_level3.add((prof_skill_level.get(i).toString()));
				}
			}
			
			//분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_skill3.size(); i++) {
					sql = "INSERT INTO tag_use("
							+ "tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_skill3.get(i));
					stmt.setString(4,prof_skill_level3.get(i).toString());
					stmt.executeUpdate();
				}
			}
		}
		catch(Exception e){
			System.out.println("insert 오류 " + e);
			e.printStackTrace();
		} 
		finally {
			freeConnection();
		}
		return dto;
	}


	// 프로필 아이디를 가지오는 메서드
	public int getProf_id(int mem_id) {
		int req_prof_id = 0;
		try {
			sql = "select * from profile where mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			rs.next();
			req_prof_id = rs.getInt("prof_id");
		} catch (Exception e) {
			System.out.println("프로필아이디 겟에서 오류");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return req_prof_id;
	}
	
	/**
	 * 태그 이름으로 번호 얻기
	 * @param tag_name
	 * @return
	 */
	private int tagNameToId(String tag_name) {
		int tag_id = 0;
		getConnection();
		try {
			String sql = "SELECT tag_id FROM tag WHERE tag_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag_name);
			rs = stmt.executeQuery();		
			if(rs.next()){
				tag_id = rs.getInt("tag_id");	//결과 있을 때 내용...
			}
			
			
			
		} catch (Exception e) {
			System.out.println("tagNameToId오류(profileDao)");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return tag_id;
	}
	/**
	 *	태그이름으로 태그 테이블에 있는지 여부 확인  false = 테이블에 없음(등록해야함) ,  true= 이미 있는 목록(건너뜀)
	 */
	private boolean tagNameCheck(String tag_name) {
		boolean tag_check = false;
		
		//null처리
		if("".equals(tag_name) || " ".equals(tag_name) || null == tag_name){
			return true;
		}
		
		getConnection();
		try {
			String sql = "SELECT tag_name FROM tag WHERE tag_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag_name);
			rs = stmt.executeQuery();
			
			
			if(rs.next()){
				return true;	//결과 있을 때 내용...
			}
			else{
				return false;	//결과 없을 때 내용..
			}

			
		}
		catch (Exception e) {
			System.out.println("tagCheck오류 : "+e);
			e.printStackTrace();
		} 
		finally{
			freeConnection();
		}
		return tag_check;
	}
	
	/**
	 * 특정 멤버의 태그를 가지고 오는 메서드
	 */
	public List member_tag(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' AND tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("member_tag 오류 " + e);
		}
		return null;
	}

	/**
	 *	프로필 수정 
	 */
	public void updateProfile(Profile dto, int mem_id) {

		sql = "update profile set prof_img=?, prof_background=?, "
				+ "prof_name=?, prof_nick=?, prof_intro=?, prof_website=?, "
				+ " where mem_id = " + mem_id;

		try {
			
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getProf_website());

			/*
			stmt.setString(8, dto.getProf_language());
			stmt.setString(9, dto.getProf_tool());
			stmt.setString(10, dto.getProf_field());
			stmt.setString(11, dto.getProf_github());
			stmt.setString(12, dto.getProf_facebook());
			stmt.setInt(13, dto.getProf_follower());
			stmt.setString(14, dto.getTag_name());
			*/
			stmt.executeUpdate();
			
		} catch (Exception err) {
			System.out.println("updateProfile : " + err);
		} finally {
			freeConnection();
		}

	}
	
	/**
	 *	프로필 리스트
	 */
	public Profile getProfile(int mem_id) {
		getConnection();
		Profile dto = new Profile();
		sql = "select distinct mem_id,prof_id, prof_name, prof_nick, prof_website, prof_github, "
				+ " prof_facebook, prof_regdate, prof_follower,prof_img,prof_background,prof_intro "
				+ " from tag join tag_use on tag.tag_id = tag_use.tag_id "
				+ " join profile on tag_use_type_id = profile.prof_id "
				+ "where mem_id="+mem_id+ "";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				dto.setMem_id(rs.getInt("mem_id"));
				dto.setProf_id(rs.getInt("prof_id"));
				dto.setProf_nick(rs.getString("prof_nick"));
				dto.setProf_name(rs.getString("prof_name"));
				dto.setProf_intro(rs.getString("prof_intro"));
				dto.setProf_img(rs.getString("prof_img"));
				dto.setProf_background(rs.getString("prof_background"));
				dto.setProf_website(rs.getString("prof_website"));
				dto.setProf_github(rs.getString("prof_github"));
				dto.setProf_facebook(rs.getString("prof_facebook"));
				
				int prof_id = rs.getInt("prof_id");
				
				dto.setTag_lang(tags_lang(prof_id));
				dto.setTag_tool(tags_tool(prof_id));
				dto.setTag_field(tags_field(prof_id));
				dto.setTag_skill(tags_skill(prof_id));
				dto.setProf_skill_level(prof_skill_levels(prof_id));
				
				dto.setProf_myPf(portfolioDao.selectListByMemId(mem_id));
			}
		} 
		catch (Exception err) {
			System.out.println("getProfile 오류: " + err);
		} finally {
			freeConnection();
		}
		
		return dto;
	}
	/**
	 * language 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_lang(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='language' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("tags_lang 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * tool 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_tool(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='tool' and tu.tag_use_type_id = ?) ";
			
			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("tags_tool 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * field 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_field(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='field' and tu.tag_use_type_id = ?) ";
			
			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("tags_field 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * skill_level 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_skill(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='skill_level' and tu.tag_use_type_id = ?) ";
			
			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("tags_skill 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * skill_level 점수 태그 리스트를 가지고 오는 메서드
	 */
	public List prof_skill_levels(int prof_id) {
		try {
			String sql = "SELECT prof_skill_level FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='skill_level' and tu.tag_use_type_id = ?) ";
			
			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("prof_skill_level"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("prof_skill_levels 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 같은 프로필 번호를 쓰는 프로필이 있는지 검사
	 * @param prof_id
	 * @return 존재하는 프로필 번호
	 */
	public int findExistingProfile(int prof_id) {
		int result = 0;
		try {
			sql = "SELECT prof_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (prof_id == rs.getInt(1)) {
					result = rs.getInt(1);
				} else {
					result = 0;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * (수정·삭제시) 작성자가 맞는지 검사
	 * @param mem_id
	 * @param prof_id
	 * @return true/false
	 */
	public boolean checkAccessRight(int mem_id, int prof_id) {
		boolean flag = false;
		try {
			sql = "SELECT mem_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(mem_id == rs.getInt(1)) {
					flag = true;
				} else {
					flag = false;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 프로필 삭제 
	 * @param eno
	 */
		
	// deleteEmp_proc.jsp
	public void deleteProfile(int prof_id){
		String sql = "delete from profile where prof_id ="+prof_id+"";
		
		try{
			conn = pool.getConnection();			
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP 연결 실패 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
	
	public Profile getProfileByNick(String nick) {
		mediaDao = new MediaDao();
		portfolioDao = new PortfolioDao();
		tagDao = new TagDao();
		Profile profile = new Profile();
		getConnection();
		try {
			String sql = "SELECT * FROM profile WHERE prof_nick=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			while (rs.next()) {
				profile.setProf_id(rs.getInt("prof_id"))
				.setMem_id(rs.getInt("mem_id"))
				.setProf_name(rs.getString("prof_name"))
				.setProf_nick(rs.getString("prof_nick"))
				.setProf_intro(rs.getString("prof_intro"))
				.setProf_img(rs.getString("prof_img"))
				.setProf_background(rs.getString("prof_background"))
				.setProf_website(rs.getString("prof_website"))
				.setProf_github(rs.getString("prof_github"))
				.setProf_facebook(rs.getString("prof_facebook"));
			}
			
			sql = "SELECT mem_email FROM member WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, profile.getMem_id());
			rs = stmt.executeQuery();
			while (rs.next()) {
				profile.setProf_email(rs.getString("mem_email"));
			}
			
			List<Tag> tagListL = tagDao.selectList("language", "prof", profile.getProf_id());
			List<Tag> tagListT = tagDao.selectList("tool", "prof", profile.getProf_id());
			List<Tag> tagListF = tagDao.selectList("field", "prof", profile.getProf_id());
			profile.setTag_lang(tagListL);
			profile.setTag_tool(tagListT);
			profile.setTag_field(tagListF);
			List<String> tag_skill = new ArrayList<String>();
			List<Integer> tag_skill_level = new ArrayList<Integer>();
			for (Tag tag : tagListL) {
				tag_skill.add(tag.getTag_name());
				tag_skill_level.add(tag.getProf_skill_level());
			}
			for (Tag tag : tagListT) {
				tag_skill.add(tag.getTag_name());
				tag_skill_level.add(tag.getProf_skill_level());
			}
			profile.setTag_skill(tag_skill);
			profile.setProf_skill_level(tag_skill_level);
			
			sql = "SELECT pf_id FROM prof_pf WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, profile.getProf_id());
			rs = stmt.executeQuery();
			List<Portfolio> myPf = new ArrayList<Portfolio>();
			while (rs.next()) {
				myPf.add(portfolioDao.selectOne(rs.getInt("pf_id")));
			}
			profile.setProf_myPf(myPf);
			return profile;
		} catch (Exception e) {
			System.out.println("getProfileByNick() 오류 : ");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return profile;
	}
	
	
	/**
	 * 닉네임으로 프로필 번호 얻기
	 * @param nick
	 * @return
	 */
	public int nickToId(String nick) {
		int prof_id = 0;
		getConnection();
		try {
			String sql = "SELECT prof_id FROM profile WHERE prof_nick=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prof_id = rs.getInt("prof_id");
			}
			return prof_id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return prof_id;
	}
	
	
	/**
	 * 프로필 번호로 닉네임 얻기
	 * 
	 * @param nick
	 * @return
	 */
	public String idToNick(int mem_id) {
		String prof_nick = null;
		getConnection();
		try {
			String sql = "SELECT prof_nick FROM profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			rs.next();
			prof_nick = rs.getString("prof_nick");
			return prof_nick;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prof_nick;
	}
	
	
}
