package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;

public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;

	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("而ㅻ꽖�뀡 �� �삤瑜� - MemberDao()");
			e.printStackTrace();
		}
	}

	/**
	 * DB �뿰寃�
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null)
				System.out.println("DB �젒�냽");
		} catch (Exception e) {
			System.out.println("DB �젒�냽 �삤瑜� - getConnection()");
			e.printStackTrace();
		}
	}

	/**
	 * DB �뿰寃� �빐�젣
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(conn, stmt, rs);
			if (conn != null)
				System.out.println("DB �젒�냽 �빐�젣");
		} catch (Exception e) {
			System.out.println("DB �젒�냽�빐�젣 �삤瑜� - freeConnection()");
			e.printStackTrace();
		}
	}

	/**
	 * 프로필 등록(프로필 테이블 + 태크 테이블에 있는 정보 등록)
	 */
	public Profile addprofile(Profile dto, int mem_id) {
		try {

			sql = "insert into profile(prof_id, mem_id, prof_img, prof_background, prof_name, prof_nick, prof_intro, prof_website, prof_regdate) "
					+ "values(prof_id.nextVal, ? , ? , ? , ? , ? , ? , ? , sysdate)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setString(2, dto.getProf_img());
			stmt.setString(3, dto.getProf_background());
			stmt.setString(4, dto.getProf_name());
			stmt.setString(5, dto.getProf_nick());
			stmt.setString(6, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());

			stmt.executeUpdate();

			// 태그 테이블 입력(언어)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'language',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			int req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			int req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();
			
			
			
			// 태그 테이블 입력(툴)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'툴(tool)',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name2());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();

			
			// 태그 테이블 입력(분야)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'분야(field)',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name3());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? )";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);

			stmt.executeUpdate();
			
			// 태그 테이블 입력(기술)
			sql = "insert into tag(tag_id, tag_type, tag_name) values(tag_id.nextVal,'기술',?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getTag_name4());

			stmt.executeUpdate();

			// 프로필 id를 가지고 오는 부분
			sql = "select * from profile where mem_id ='" + mem_id + "'" + "order by prof_regdate desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_prof_id = rs.getInt("prof_id");

			// 태그 유즈테이블에서 특정 사람이 사용한 태그를 가져오기위한 부분
			sql = "select * from tag order by tag_id desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			req_tag_id = rs.getInt("tag_id");

			// 태그 유즈 테이블 입력
			sql = "insert into tag_use(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level)"
					+ "values(tag_use_id.nextVal, 'profile', ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.setInt(2, req_tag_id);
			stmt.setInt(3, dto.getProf_skill_level());

			stmt.executeUpdate();
			System.out.println("ok");

		}

		catch (Exception err) {
			System.out.println("addprofile에서 오류 " + err);
		} finally {
			freeConnection();
		}
		return dto;
	}

	/**
	 * 관심 툴  입력
	 */
	public Profile addprofile2(Profile dto, int mem_id) {
		try {
			
		}

		catch (Exception err) {
			System.out.println("addprofile에서 오류 " + err);
		} finally {
			freeConnection();
		}
		return dto;
	}

	
	/**
	 * 관심 분야 입력
	 */
	public Profile addprofile3(Profile dto, int mem_id) {
		try {
			
		}

		catch (Exception err) {
			System.out.println("addprofile에서 오류 " + err);
		} finally {
			freeConnection();
		}
		return dto;
	}
	
	/**
	 *  스킬 및 점수 입력
	 */
	public Profile addprofile4(Profile dto, int mem_id) {

		try {
			
		}

		catch (Exception err) {
			System.out.println("addprofile에서 오류 " + err);
		} finally {
			freeConnection();
		}
		return dto;
	}

	/**
	 * �봽濡쒗븘 �닔�젙 �엯�젰�뻽�뜕 寃껋쓣 遺덈윭�삩�떎.
	 * 
	 * @param profile
	 */

	public Profile getProfile(int mem_id) {
		String sql = "SELECT PROFILE.PROF_IMG, PROFILE.PROF_BACKGROUND, PROFILE.PROF_NAME, "
				+ "PROFILE.PROF_NICK, PROFILE.PROF_INTRO, TAG_USE.TAG_USE_TYPE, TAG_USE.TAG_USE_TYPE, TAG_USE.TAG_USE_TYPE,"
				+ "PROFILE.WEBSITE, PROFILE.GITBUB, PROFILE.FACEBOOK, PROFILE.FOLLOWER, TAG_USE.TAG_USE_TYPE,"
				+ "TAG_USE.PROF_SKILL_LEVEL " + "FROM PROFILE, TAG_USE"
				+ "WHERE PROFILE.MEM_ID = TAG_USE.TAG_USE_TYPE_ID and TAG_USE.TAG_ID = TAG.TAG_ID";

		Profile dto = new Profile();

		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				dto.setProf_id(mem_id);
				dto.setProf_img(rs.getString("prof_img"));
				dto.setProf_background(rs.getString("prof_background"));
				dto.setProf_name(rs.getString("prof_name"));
				dto.setProf_nick(rs.getString("prof_nick"));
				dto.setProf_intro(rs.getString("prof_intro"));
				dto.setProf_language(rs.getString("prof_laguage"));
				dto.setProf_tool(rs.getString("prof_tool"));
				dto.setProf_field(rs.getString("prof_field"));
				dto.setProf_website(rs.getString("prof_website"));
				dto.setProf_github(rs.getString("prof_github"));
				dto.setProf_facebook(rs.getString("prof_facebook"));
				dto.setProf_follower(rs.getInt("prof_follower"));
				dto.setTag_name(rs.getString("tag_name"));
				dto.setProf_skill_level(rs.getInt("prof_skill_level"));

			}
		} catch (Exception err) {
			System.out.println("profile() : " + err);
		} finally {
			freeConnection();
		}
		return dto;

	}

	/**
	 * �봽濡쒗븘 �닔�젙 ��(�궡�슜媛� �닔�젙)
	 * 
	 * @param dto
	 */

	public void updateProfile(Profile dto) {

		String sql = "UPDATE PROFILE SET prof_img=?, prof_background=?, prof_name=?, prof_nick=?, "
				+ "prof_intro=?, prof_language=?, prof_tool=?, prof_field=?, prof_website=?, "
				+ "prof_github=?, prof_facebook=?, prorof_follower=?" + ",Tag_name=?, prof_skill_level=?"
				+ "WHERE prof_id=?";

		sql = "UPDATE TAG_USE SET TAG_TYPE=? AND PROF_SKILL_LEVEL=?";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getProf_language());
			stmt.setString(7, dto.getProf_tool());
			stmt.setString(8, dto.getProf_field());
			stmt.setString(9, dto.getProf_website());
			stmt.setString(10, dto.getProf_github());
			stmt.setString(11, dto.getProf_facebook());
			stmt.setInt(12, dto.getProf_follower());
			stmt.setString(14, dto.getTag_name());
			stmt.setInt(15, dto.getProf_skill_level());
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP �뿰寃� �떎�뙣 : " + err);
		} finally {
			freeConnection();
		}

	}
}