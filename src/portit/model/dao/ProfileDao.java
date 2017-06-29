package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

			sql = "insert into profile(prof_id, mem_id, prof_img, prof_background, prof_name, prof_nick, prof_intro, prof_website, prof_regdate, prof_follower) "
					+ "values(prof_id.nextVal, ? , ? , ? , ? , ? , ? , ? , sysdate, ?)";

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setString(2, dto.getProf_img());
			stmt.setString(3, dto.getProf_background());
			stmt.setString(4, dto.getProf_name());
			stmt.setString(5, dto.getProf_nick());
			stmt.setString(6, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());
			stmt.setInt(8, dto.getProf_follower());

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
	 *	프로필 수정 
	 */
	public void updateProfile(Profile dto, int mem_id) {

		sql = "update profile set prof_img='?', prof_background='?', "
				+ "prof_name='?', prof_nick='?', prof_intro='?', prof_website='?'"
				+ " where mem_id = " + mem_id;

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
			System.out.println("updateProfile : " + err);
		} finally {
			freeConnection();
		}

	}
	
	/**
	 *	프로필 리스트
	 */
	public List getProfile(Profile dto, int mem_id) {
		ArrayList list = new ArrayList();
		sql = "select * from profile";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setProf_id(rs.getInt("prof_id"));
				profile.setProf_nick(rs.getString("prof_nick"));
				profile.setTag_name(rs.getString("tag_name"));
				profile.setTag_name2(rs.getString("tag_name2"));
				profile.setTag_name3(rs.getString("tag_name3"));
				profile.setTag_name4(rs.getString("tag_name4"));
				profile.setProf_img(rs.getString("prof_img"));
				profile.setProf_background(rs.getString("prof_background"));
				profile.setProf_name(rs.getString("prof_name"));
				profile.setProf_follower(rs.getInt("prof_follower"));
				profile.setProf_website(rs.getString("prof_website"));
				profile.setProf_github(rs.getString("prof_github"));
				profile.setProf_facebook(rs.getString("prof_facebook"));
				profile.setProf_regdate(rs.getDate("prof_regdate"));
				profile.setProf_follower(rs.getInt("prof_follower"));
				profile.setProf_language(rs.getString("prof_language"));
				profile.setProf_tool(rs.getString("prof_tool"));
				profile.setProf_field(rs.getString("prof_field"));
				profile.setProf_skill_level(rs.getInt("prof_skill_level"));
				

				list.add(profile);
			}
		} 
		catch (Exception err) {
			System.out.println("updateProfile : " + err);
		} finally {
			freeConnection();
		}
		
		return list;
	}
}